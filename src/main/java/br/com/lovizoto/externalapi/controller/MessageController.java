package br.com.lovizoto.externalapi.controller;


import br.com.lovizoto.commons.dto.FirstContactDto;
import br.com.lovizoto.externalapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final UserService userService;

    @Autowired
    public MessageController(UserService userService) {
        this.userService = userService;
    }



    //PostMapping with session


}
