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

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final UserService userService;
    private final MessageOrchestrator messageOrchestrator;

    public MessageController(UserService userService, MessageOrchestrator messageOrchestrator) {
        this.userService = userService;
        this.messageOrchestrator = messageOrchestrator;
    }

//    @PostMapping
//    public ResponseEntity<BatchResponse> handleMessage(@Valid @RequestBody MessageDto messageDto) {
//        return ResponseEntity.ok(messageOrchestrator.process(messageDto));
//    }


    @PostMapping
    public ResponseEntity<BatchResponse> handleMessage(@Valid @RequestBody MessageDto messageDto) {
        // Converts the single message into a one-item batch;
        BatchRequest batchRequest = new BatchRequest();
        batchRequest.setExternalId(messageDto.getExternalId());
        batchRequest.setSource(messageDto.getSource());
        batchRequest.setMessages(List.of(messageDto.getMessage())); // Usa List.of para criar uma lista imutável

        // Uses the same batch processing method;
        return ResponseEntity.ok(messageOrchestrator.processBatch(batchRequest));
    }

    @PostMapping ("/batch")
    public ResponseEntity<BatchResponse> handleBatch(@Valid @RequestBody BatchRequest batchRequest) {
        // This endpoint now calls the same method, maintaining consistency;
        return ResponseEntity.ok(messageOrchestrator.processBatch(batchRequest));
    }



}
