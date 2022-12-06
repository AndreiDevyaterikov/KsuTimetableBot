package timetablebot.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import timetablebot.models.Faculty;
import timetablebot.models.Lesson;
import timetablebot.services.RequestService;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final WebClient client;

    @Override
    public List<Lesson> getTodayLessonsForGroup(String groupName) {
        return client
                .get()
                .uri("/timetable/today/" + groupName)
                .retrieve()
                .bodyToFlux(Lesson.class)
                .collectList()
                .block();
    }

    @Override
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
