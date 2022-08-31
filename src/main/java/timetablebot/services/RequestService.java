package timetablebot.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class RequestService {
    private final WebClient client;

    public String postRequest(MultiValueMap<String, String> requestParams, String uri) {
        return client
                .post()
                .uri(uri)
                .bodyValue(requestParams)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getRequest(String uri) {
        return client
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
