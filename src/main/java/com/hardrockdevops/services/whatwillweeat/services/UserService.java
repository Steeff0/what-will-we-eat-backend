package com.hardrockdevops.services.whatwillweeat.services;

import com.hardrockdevops.services.whatwillweeat.exceptions.ServiceException;
import com.hardrockdevops.services.whatwillweeat.models.User;
import com.hardrockdevops.services.whatwillweeat.repositories.UserRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@CommonsLog
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void AddUser(User user) {
        userRepository.save(user);
    }

    public User getUser(long id) throws ServiceException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ServiceException("No User found with id " + id);
        }
        return opt.get();
    }

    public User getUser(String email) throws ServiceException {
        User user = new User();
        user.setEmail(email);
        Optional<User> opt = userRepository.findOne(Example.of(user));
        if (opt.isEmpty()) {
            throw new ServiceException("No User found with email " + email);
        }
        return opt.get();
    }
}
