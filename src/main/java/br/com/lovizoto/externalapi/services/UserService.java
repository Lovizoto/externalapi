package br.com.lovizoto.externalapi.services;


import br.com.lovizoto.externalapi.model.User;
import br.com.lovizoto.externalapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //VERSION WITHOUT DTO
    public List<User> findAll() {
        logger.info("Find All Users");
        return userRepository.findAll();
    }

    public User save(User user) {
        logger.info("Save User");
        return userRepository.save(user);
    }

    public User findById(String id) {
        logger.info("Find User by ID");
        return userRepository.findById(id).orElse(null); //make a exception handler
    }

    public Optional<User> findByExternalId(String externalId) {
        logger.info("Find User by External ID");
        return userRepository.findByExternalId(externalId); //dont need a exception handler, yet!
    }

    //Can an update be considered here?
    //Can a delete method be considered here?

}
