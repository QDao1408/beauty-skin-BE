package online.beautyskin.beauty.service;

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

    public User register(UserRequest userRequest) {
        User user = new User();
        passwordEncoder.encode(userRequest.getPassword());

        user.roleEnums = RoleEnums.USER;
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUsername(userRequest.getUsername());
        user.setMail(userRequest.getEmail());
        // save
        User u = authenticationRepository.save(user);
        return u;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getUsername(),
                                authenticationRequest.getPassword()
                        )
                );
        }catch (Exception e){
            throw new NullPointerException("tài khoản hoặc mật khẩu không đúng");
        }
        User user = authenticationRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(authenticationRequest.getUsername()));
        String token = tokenService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setToken(token);
        authenticationResponse.setId(user.getId());
        authenticationResponse.setMail(user.getMail());
        authenticationResponse.setUsername(user.getUsername());
        authenticationResponse.setPassword(user.getPassword());
        authenticationResponse.setRoleEnum(user.getRoleEnums());

        return authenticationResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticationRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found"));

    }
}
