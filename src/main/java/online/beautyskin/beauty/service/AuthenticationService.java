package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.UserRequest;
import online.beautyskin.beauty.enums.RoleEnums;
import online.beautyskin.beauty.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
