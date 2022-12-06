package timetablebot.makers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import timetablebot.enums.Emojis;
import timetablebot.services.RequestServiceImpl;

@Component
@AllArgsConstructor
public class TextMaker {

    private final RequestServiceImpl requestServiceImpl;

    public String getText(String messageText) {

        if (messageText.equals("/faculties")) {
            var faculties = requestServiceImpl.getFaculties();
            StringBuilder message = new StringBuilder();
            for (var faculty : faculties) {
                message.append(faculty.getTitle());
                message.append("\n");
            }
            return message.toString();
        }
        if (messageText.equals("/lesson_group_today")) {
            return "Введите название группы";
        }
        if (messageText.matches("[0-9]{2}-[А-Я]{1,2}[б|м][о|з]-[0-9]")) {
            var lessonsToday = requestServiceImpl.getTodayLessonsForGroup(messageText);
            StringBuilder text = new StringBuilder();
            for (var lessonToday : lessonsToday) {
                text.append("Предмет: ")
                        .append(lessonToday.getLessonName())
                        .append("\n")
                        .append("Начало: ")
                        .append(lessonToday.getLessonTimeStart())
                        .append("\n")
                        .append("Конец: ")
                        .append(lessonToday.getLessonTimeEnd())
                        .append("\n");
                if(lessonToday.getTeacher() != null) {
                    text.append("Преподаватель: ")
                            .append(lessonToday.getTeacher().getTitle())
                            .append("\n");
                }
                if(lessonToday.getCabinet() != null){
                    text.append("Аудитория: ")
                            .append(lessonToday.getCabinet().getTitle())
                            .append("\n");
                }
                if(lessonToday.getSubgroup() != null) {
                    text.append("Подгруппа: ")
                            .append(lessonToday.getSubgroup())
                            .append("\n");
                }
                text.append("Вид пары: ")
                        .append(lessonToday.getLessonType())
                        .append("\n")
                        .append("Неделя: ")
                        .append(lessonToday.getTypeWeek())
                        .append("\n")
                        .append("==================")
                        .append("\n");
            }
            return text.toString();
        } else {
            return "Я не знаю такой команды " + Emojis.NO_MONTH.getEmojiCode();
        }
    }
}
