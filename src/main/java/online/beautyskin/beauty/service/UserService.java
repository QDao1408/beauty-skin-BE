package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User update(User user) {
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


}
