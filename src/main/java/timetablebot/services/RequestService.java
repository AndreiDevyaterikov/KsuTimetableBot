package timetablebot.services;

import timetablebot.models.Faculty;
import timetablebot.models.Lesson;

import java.util.List;

public interface RequestService {

    /**
     * Запрос на получение расписания на текущий день для группы
     *
     * @param groupName Имя группы для поиска расписания
     * @return {@link Lesson} Список занятий на текущий день
     */
    List<Lesson> getTodayLessonsForGroup(String groupName);

    /**
     * Запрос на получение всех факультетов\институтов
     *
     * @return {@link Faculty} Список факультетов\институтов
     */
    List<Faculty> getFaculties();
}
