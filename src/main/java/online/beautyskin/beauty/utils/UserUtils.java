package online.beautyskin.beauty.utils;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.AuthenticationRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils implements ApplicationContextAware {

    private static AuthenticationRepository authenticationRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        authenticationRepository = applicationContext.getBean(AuthenticationRepository.class);
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return authenticationRepository.findByUsername(username).orElseThrow();
    }
}
