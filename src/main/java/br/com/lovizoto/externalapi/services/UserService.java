package br.com.lovizoto.externalapi.services;


import br.com.lovizoto.commons.dto.FirstContactDto;
import br.com.lovizoto.externalapi.mapper.UserMapper;
import br.com.lovizoto.externalapi.model.User;
import br.com.lovizoto.externalapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Cacheable(value = "users", key = "#externalId + '|' + #source" )
    public User searchFromCache(String externalId, String source) {
        return userRepository
                .findByExternalIdAndSource(externalId, source)
                .orElseGet(() -> createUser(externalId, source));
    }

    private User createUser(String externalId, String source) {
        User user = new User();
        user.setExternalId(externalId);
        user.setSource(source);

        return userRepository.save(user);
    }


    //CACHEPUT
    //CACHEEVICT








}
