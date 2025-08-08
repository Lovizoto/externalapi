package br.com.lovizoto.externalapi.services;

import br.com.lovizoto.commons.dto.BatchResponse;
import br.com.lovizoto.externalapi.client.ChatbotApiClient;
import br.com.lovizoto.externalapi.dto.MessageDto;
import br.com.lovizoto.externalapi.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageOrchestrator {


    private final UserService userService;
    private final ChatbotApiClient chatbotApiClient;

    public MessageOrchestrator(UserService userService, ChatbotApiClient chatbotApiClient) {
        this.userService = userService;
        this.chatbotApiClient = chatbotApiClient;
    }

    public BatchResponse process(MessageDto messageDto) {

        //User manager with cache
        User user = userService.searchFromCache(messageDto.getExternalId(), messageDto.getSource());

        String sessionId = chatbotApiClient.createsession(user.getId());
        String response = chatbotApiClient.sendMessage(sessionId, messageDto.getMessage());

        //modificate chatbot-commons and make a constructor for this code below
        BatchResponse batchResponse = new BatchResponse();
        batchResponse.setResponses(List.of(response));
        batchResponse.setSessionId(sessionId);

        return batchResponse;


    }

    //verify if necessary a batch version

}
