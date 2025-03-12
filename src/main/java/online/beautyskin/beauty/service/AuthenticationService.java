package online.beautyskin.beauty.service;

import lombok.SneakyThrows;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.AuthenticationRequest;
import online.beautyskin.beauty.entity.request.UserRequest;
import online.beautyskin.beauty.entity.respone.AuthenticationResponse;
import online.beautyskin.beauty.enums.RoleEnums;
import online.beautyskin.beauty.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
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

    @SneakyThrows
    public User register(UserRequest userRequest) {
        User user = new User();

        if(userRequest.getConfirmPassword().equals(userRequest.getPassword())) {

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

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        User user = findByUsernameOrEmail(authenticationRequest.getUsername());
        if(authenticationRepository.existsByIdAndIsDeletedFalse(user.getId())) {

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), // Always use username for authentication
                                authenticationRequest.getPassword()
                        )
                );
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
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail)));
    }

    public List<User> getAllUsers() {
        return authenticationRepository.findByIsDeletedFalse();
    }


}
