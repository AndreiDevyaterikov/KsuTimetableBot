package timetablebot.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import timetablebot.models.Faculty;
import timetablebot.models.Timetable;

import java.util.List;

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

    public List<Timetable> getLessonsToday(String groupName) {
        return client
                .get()
                .uri("/timetable/today/" + groupName)
                .retrieve()
                .bodyToFlux(Timetable.class)
                .collectList()
                .block();
    }

    public List<Faculty> getFaculties() {
        return client
                .get()
                .uri("/ksu/faculties")
                .retrieve()
                .bodyToFlux(Faculty.class)
                .collectList()
                .block();
    }
}
