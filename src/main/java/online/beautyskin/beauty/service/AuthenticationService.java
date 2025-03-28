package online.beautyskin.beauty.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.SneakyThrows;
import online.beautyskin.beauty.entity.EmailDetails;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.*;
import online.beautyskin.beauty.entity.respone.AuthenticationResponse;
import online.beautyskin.beauty.entity.respone.StaffResponse;
import online.beautyskin.beauty.entity.respone.UserListResponse;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
import online.beautyskin.beauty.enums.RoleEnums;
import online.beautyskin.beauty.repository.AuthenticationRepository;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.repository.StaffTaskRepository;
import online.beautyskin.beauty.repository.UserRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StaffTaskRepository staffTaskRepository;
    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    public User register(UserRequest userRequest) {
        User user = new User();

        if (userRequest.getConfirmPassword().equals(userRequest.getPassword())) {

            passwordEncoder.encode(userRequest.getPassword());

            user.roleEnums = RoleEnums.USER;
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setUsername(userRequest.getUsername());
            user.setMail(userRequest.getEmail());
            user.setFullName(userRequest.getFullName());
            user.setActive(true);
        } else {
            throw new SQLIntegrityConstraintViolationException("Xác nhận mặt khẩu không khớp");
        }
        User u = null;
        try {
            u = authenticationRepository.save(user);
        } catch (Exception exception) {
            throw new SQLIntegrityConstraintViolationException("Username hoặc gmail đã tồn tại");
        }
        // save
        return u;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        User user = findByUsernameOrEmail(authenticationRequest.getUsername());
        if (authenticationRepository.existsByIdAndIsDeletedFalse(user.getId())) {

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), // Always use username for authentication
                                authenticationRequest.getPassword()));
            } catch (Exception e) {
                throw new NullPointerException("tài khoản hoặc mật khẩu không đúng");
            }

            String token = tokenService.generateToken(user);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();

            authenticationResponse.setToken(token);
            authenticationResponse.setId(user.getId());
            authenticationResponse.setMail(user.getMail());
            authenticationResponse.setUsername(user.getUsername());
            authenticationResponse.setPassword(user.getPassword());
            authenticationResponse.setRoleEnum(user.getRoleEnums());
            authenticationResponse.setDeleted(false);
            authenticationResponse.setActive(true);
            return authenticationResponse;
        } else {
            throw new NullPointerException("tài khoản không tồn tại");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return findByUsernameOrEmail(usernameOrEmail);
    }

    private User findByUsernameOrEmail(String usernameOrEmail) {
        return authenticationRepository.findByUsername(usernameOrEmail)
                .orElseGet(() -> authenticationRepository.findByMail(usernameOrEmail)
                        .orElseThrow(() -> new UsernameNotFoundException(
                                "User not found with username or email: " + usernameOrEmail)));
    }

    public List<UserListResponse> getAllUsers() {
        List<User> users = authenticationRepository.findByIsDeletedFalse();
        List<UserListResponse> userListResponses = new ArrayList<>();

        for (User user : users) {
            if (user!=null) {
                UserListResponse userListResponse = new UserListResponse();
                userListResponse.setId(user.getId());
                userListResponse.setFullName(user.getFullName());
                userListResponse.setMail(user.getMail());
                userListResponse.setPhone(user.getPhone());
                userListResponse.setActive(user.isActive());
                userListResponse.setRole(user.getRoleEnums());
                userListResponse.setTotalExpenditures(orderRepository.getTotalSpentByCustomer(user.getId(), OrderStatusEnums.DELIVERED, PaymentStatusEnums.PAID));
                userListResponses.add(userListResponse);
            }
        }
        return userListResponses;
    }

    public List<StaffResponse> getAllStaff() {
        List<User> staff = userRepository.findAllStaff(RoleEnums.STAFF);
        List<StaffResponse> staffResponses = new ArrayList<>();
        for (User user : staff) {
            if (user!=null) {
                StaffResponse staffResponse = new StaffResponse();
                staffResponse.setId(user.getId());
                staffResponse.setFullName(user.getFullName());
                staffResponse.setMail(user.getMail());
                staffResponse.setPhone(user.getPhone());
                staffResponse.setActive(user.isActive());
                staffResponse.setRole(user.getRoleEnums());
                staffResponse.setCompletedOrders(staffTaskRepository.countCompletedOrdersByStaff(user.getId()));
                staffResponses.add(staffResponse);
            }
        }
        return staffResponses;
    }

    public void forgotPassword(ForgotPasswordRequest request) {
        User user = authenticationRepository.findByMail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setReceiver(user.getMail()); // email gửi tới user
        emailDetails.setSubject("Reset Password For Account " + user.getMail());
        emailDetails.setMsgBody("hello abc");
        emailDetails.setButtonValue("Reset Password");
        emailDetails.setFullName(user.getFullName());
        emailDetails.setLink("http://beautyskinshop.online/reset-password?token=" + tokenService.generateToken(user));

        Runnable r = new Runnable() { // cho pass api trước r gửi mail sau
            @Override
            public void run() {
                emailService.sendResetPasswordMail(emailDetails);
            }
        };
        new Thread(r).start();

    }

    @SneakyThrows
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = userUtils.getCurrentUser();
        String pass = resetPasswordRequest.getPassword();
        String confirmPass = resetPasswordRequest.getConfirmPassword();
        if (!pass.equals(confirmPass)) {
            throw new SQLIntegrityConstraintViolationException("Confirm pass and pass do not match");
        }

        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        authenticationRepository.save(user);
    }

    public AuthenticationResponse loginGoogle(LoginGoogleRequest googleRequest) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(googleRequest.getToken());
            String email = decodedToken.getEmail();
            User user = authenticationRepository.findByMail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            if (user == null) { // email chưa được dky thì dky tk mời
                user = new User();
                user.setFullName(decodedToken.getName());
                user.setMail(email);
                user.setUsername(email);
                user.setRoleEnums(RoleEnums.USER);
                authenticationRepository.save(user);
            }

            String token = tokenService.generateToken(user);

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setMail(user.getMail());
            authenticationResponse.setId(user.getId());
            authenticationResponse.setFullName(user.getFullName());
            authenticationResponse.setUsername(user.getUsername());
            authenticationResponse.setRoleEnum(user.getRoleEnums());
            authenticationResponse.setToken(token);

            return authenticationResponse;

        } catch (FirebaseAuthException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
