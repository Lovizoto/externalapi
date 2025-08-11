package br.com.lovizoto.externalapi.client;


import br.com.lovizoto.commons.dto.MessageRequest;
import br.com.lovizoto.commons.dto.MessageResponse;
import br.com.lovizoto.commons.dto.SessionRequest;
import br.com.lovizoto.commons.dto.SessionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "",
        url = "",
        configuration = FeignClient.class
)  //CONFIG FEIGN AND YML
public interface ChatbotApiClient {

    @PostMapping("/chatbot/sessions")
    SessionResponse createSession(@RequestBody SessionRequest sessionRequest);

    @PostMapping("/chatbot/sessions/{sessionId}/messages")
    MessageResponse sendMessage(@PathVariable String sessionId,
                                @RequestBody MessageRequest messageRequest);


}
