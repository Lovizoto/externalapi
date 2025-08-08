package br.com.lovizoto.externalapi.controller;


import br.com.lovizoto.commons.dto.BatchResponse;
import br.com.lovizoto.externalapi.dto.BatchRequest;
import br.com.lovizoto.externalapi.dto.MessageDto;
import br.com.lovizoto.externalapi.services.MessageOrchestrator;
import br.com.lovizoto.externalapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final UserService userService;
    private final MessageOrchestrator messageOrchestrator;

    public MessageController(UserService userService, MessageOrchestrator messageOrchestrator) {
        this.userService = userService;
        this.messageOrchestrator = messageOrchestrator;
    }

    @PostMapping
    public ResponseEntity<BatchResponse> handleMessage(@Valid @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(messageOrchestrator.process(messageDto));
    }

    //everything will be batch - ???
//    @PostMapping ("/batch")
//    public ResponseEntity<BatchResponse> handleBatch(@Valid @RequestBody BatchRequest batchRequest) {
//        return ResponseEntity.ok(messageOrchestrator.processBatch(batchRequest));
//    }


}
