package br.com.lovizoto.externalapi.mapper.decorators;

import br.com.lovizoto.commons.dto.FirstContactDto;
import br.com.lovizoto.externalapi.mapper.UserMapper;
import br.com.lovizoto.externalapi.model.User;
import br.com.lovizoto.externalapi.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class UserMapperDecorator implements UserMapper {


    private UserService userService;

    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Override
    public User fromDto(FirstContactDto firstContactDto) {
        return userService.findByExternalId(firstContactDto.getExternalId()).orElseGet(() -> {
            User user = userMapper.fromDto(firstContactDto);
            user.setCreatedAt(LocalDateTime.now());
            return user;
        });
    }


}
