package br.com.lovizoto.externalapi.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "",
        url = "",
        configuration = FeignClient.class
)  //CONFIG FEIGN AND YML
public interface ChatbotApiClient {

    @PostMapping("/chatbot/sessions")
    String createsession(@RequestBody String session);

    @PostMapping("chatbot/sessions/{sessionId}/messages")
    String sendMessage(@PathVariable String sessionId,
                       @RequestBody String message);


}
