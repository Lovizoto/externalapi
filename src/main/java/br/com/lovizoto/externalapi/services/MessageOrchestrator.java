package br.com.lovizoto.externalapi.services;


import br.com.lovizoto.commons.dto.MessageRequest;
import br.com.lovizoto.commons.dto.MessageResponse;
import br.com.lovizoto.commons.dto.SessionRequest;
import br.com.lovizoto.commons.dto.SessionResponse;
import br.com.lovizoto.externalapi.client.ChatbotApiClient;
import br.com.lovizoto.externalapi.dto.BatchRequest;
import br.com.lovizoto.externalapi.dto.BatchResponse;
import br.com.lovizoto.externalapi.dto.MessageDto;
import br.com.lovizoto.externalapi.model.User;
import org.jetbrains.annotations.NotNull;
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




    public BatchResponse processBatch(BatchRequest batchRequest) {

        // 1. Identifies the user (and creates them if it’s the first time);
        User user = userService.searchFromCache(batchRequest.getExternalId(), batchRequest.getSource());

        // 2. Retrieves/Creates the UNIQUE SESSION for this user.
        // The session is retrieved from the cache or created. It is the same for all messages in this batch.
        String sessionId = sessionCacheService.getSessionId(user.getId());

        if (sessionId == null) {

            SessionRequest sessionRequest = new SessionRequest(user.getId());

            SessionResponse sessionResponse = chatbotApiClient.createSession(sessionRequest);
            sessionId = sessionResponse.sessionId();

            sessionCacheService.cacheSessionId(user.getId(), sessionId); // Stores the new session in the cache;
        }

        // Sends ALL messages in the batch to the SAME SESSION;
        List<String> responses = new ArrayList<>();
        for (String message : batchRequest.getMessages()) {

            MessageRequest messageRequest = new MessageRequest(message);
            MessageResponse messageResponse = chatbotApiClient.sendMessage(sessionId, messageRequest);
            responses.add(messageResponse.response());
        }

        // 4. Builds the batch response;
        BatchResponse batchResponse = new BatchResponse();
        batchResponse.setResponses(responses);
        batchResponse.setSessionId(sessionId); // The sessionId is the same for all responses;

        return batchResponse;
    }
}


