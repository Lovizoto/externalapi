package br.com.lovizoto.externalapi.mapper;

import br.com.lovizoto.commons.dto.FirstContactDto;
import br.com.lovizoto.externalapi.dto.MessageDto;
import br.com.lovizoto.externalapi.mapper.decorators.UserMapperDecorator;
import br.com.lovizoto.externalapi.model.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    public User fromDto(MessageDto messageDto);

}
