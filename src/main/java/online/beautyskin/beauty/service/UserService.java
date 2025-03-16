package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.PasswordResetToken;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.UserUpdateRequest;
import online.beautyskin.beauty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User update(UserUpdateRequest user, long id) {
        User newUser = userRepository.findById(id);
        newUser.setFullName(user.getFullName());
        newUser.setPhone(user.getPhone());
        newUser.setGender(user.getGender());
        newUser.setBirthday(user.getBirthday());
        return userRepository.save(newUser);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User delete(User user) {
        user.setDeleted(true);
        return userRepository.save(user);
    }

    public User inActiveUser(User user) {
        user.setActive(false);
        return userRepository.save(user);
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }


    public User lockUser(long id) {
        User user = userRepository.findById(id);
        user.setActive(false);
        return userRepository.save(user);
    }

    public User unLockUser(long id) {
        User user = userRepository.findById(id);
        user.setActive(true);
        return userRepository.save(user);
    }


}
