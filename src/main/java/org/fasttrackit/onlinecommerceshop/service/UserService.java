package org.fasttrackit.onlinecommerceshop.service;
import org.fasttrackit.onlinecommerceshop.domain.User;
import org.fasttrackit.onlinecommerceshop.expection.ResourceNotFoundException;
import org.fasttrackit.onlinecommerceshop.persistance.UserRepository;
import org.fasttrackit.onlinecommerceshop.transfer.user.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(SaveUserRequest request) throws ValidationException {
        if (emailAlreadyExist(request.getEmail())) {
            LOGGER.info("Email is already registered");
            throw new ValidationException("Email is already registered");


        } else {
            LOGGER.info("Creating user: {}", request);
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());

            return userRepository.save(user);
        }
    }

    public User getUser(Long id) {
        LOGGER.info("Retrieving user: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public User getUser(String password, String email) {
        LOGGER.info("Retrieving user: {},", password, email);
        return userRepository.findByEmailAndPassword(password, email).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
    }

    private boolean emailAlreadyExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}

