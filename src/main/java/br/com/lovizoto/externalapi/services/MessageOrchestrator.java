package br.com.lovizoto.externalapi.services;

import br.com.lovizoto.commons.dto.BatchResponse;
import br.com.lovizoto.externalapi.client.ChatbotApiClient;
import br.com.lovizoto.externalapi.dto.BatchRequest;
import br.com.lovizoto.externalapi.dto.MessageDto;
import br.com.lovizoto.externalapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageOrchestrator {


    private final UserService userService;
    private final ChatbotApiClient chatbotApiClient;
    private final SessionCacheService sessionCacheService;

    public MessageOrchestrator(UserService userService, ChatbotApiClient chatbotApiClient, SessionCacheService sessionCacheService) {
        this.userService = userService;
        this.chatbotApiClient = chatbotApiClient;
        this.sessionCacheService = sessionCacheService;
    }

//    public BatchResponse process(MessageDto messageDto) {
//
//        //User manager with cache
//        User user = userService.searchFromCache(messageDto.getExternalId(), messageDto.getSource());
//
//        String sessionId = chatbotApiClient.createsession(user.getId());
//        String response = chatbotApiClient.sendMessage(sessionId, messageDto.getMessage());
//
//        //modificate chatbot-commons and make a constructor for this code below
//        BatchResponse batchResponse = new BatchResponse();
//        batchResponse.setResponses(List.of(response));
//        batchResponse.setSessionId(sessionId);
//
//        return batchResponse;
//
//
//    }

    public BatchResponse processBatch(BatchRequest batchRequest) {

        // 1. Identifies the user (and creates them if it’s the first time);
        User user = userService.searchFromCache(batchRequest.getExternalId(), batchRequest.getSource());

        // 2. Retrieves/Creates the UNIQUE SESSION for this user.
        // The session is retrieved from the cache or created. It is the same for all messages in this batch.
        String sessionId = sessionCacheService.getSessionId(user.getId());
        if (sessionId == null) {
            sessionId = chatbotApiClient.createsession(user.getId());
            sessionCacheService.cacheSessionId(user.getId(), sessionId); // Stores the new session in the cache;
        }

        // Sends ALL messages in the batch to the SAME SESSION;
        List<String> responses = new ArrayList<>();
        for (String message : batchRequest.getMessages()) {
            String response = chatbotApiClient.sendMessage(sessionId, message);
            responses.add(response);
        }

        // 4. Builds the batch response;
        BatchResponse batchResponse = new BatchResponse();
        batchResponse.setResponses(responses);
        batchResponse.setSessionId(sessionId); // The sessionId is the same for all responses;

        return batchResponse;
    }
}


