package timetablebot.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import timetablebot.constants.Constants;
import timetablebot.constants.Messages;
import timetablebot.models.Lesson;
import timetablebot.services.MessageService;
import timetablebot.services.RequestService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RequestService requestService;
    private boolean waitingGroupName;

    @Override
    public String createMessageByCommand(String command) {
        switch (command) {
            case "/faculties" -> {
                return createFacultiesMessage();
            }
            case "/lessons_for_group_today" -> {
                waitingGroupName = true;
                return Messages.ENTER_GROUP_NAME;
            }
            default -> {
                return Messages.UNKNOWN_COMMAND;
            }
        }
    }

    @Override
    public String createMessageByMessage(String message) {
        if (waitingGroupName) {
            if (message.matches("[0-9]{2}-[А-Я]{1,2}[б|м][о|з]-[0-9]")){
                var lessonsForGroupToday = requestService.getTodayLessonsForGroup(message);
                waitingGroupName = false;
                return createDescriptionLessonsMessage(lessonsForGroupToday);
            } else {
                waitingGroupName = false;
                return String.format(Messages.UNKNOWN_GROUP_NAME, message);
            }
        }
        return null;
    }

    private String createFacultiesMessage() {
        var faculties = requestService.getFaculties();
        StringBuilder message = new StringBuilder();
        for (var faculty : faculties) {
            message.append(faculty.getTitle());
            message.append("\n");
        }
        return message.toString();
    }

    private String createDescriptionLessonsMessage(List<Lesson> lessons) {
        StringBuilder message = new StringBuilder();
        lessons.forEach(lesson -> {
            message.append(Constants.SUBJECT).append(lesson.getLessonName()).append("\n")
                    .append(Constants.START_TIME).append(lesson.getLessonTimeStart()).append("\n")
                    .append(Constants.END_TIME).append(lesson.getLessonTimeEnd()).append("\n");
            if(lesson.getTeacher() != null) {
                message.append(Constants.TEACHER).append(lesson.getTeacher().getTitle()).append("\n");
            }
            if(lesson.getCabinet() != null){
                message.append(Constants.CABINET).append(lesson.getCabinet().getTitle()).append("\n");
            }
            if(lesson.getSubgroup() != null) {
                message.append(Constants.SUBGROUP).append(lesson.getSubgroup()).append("\n");
            }
            message.append(Constants.TYPE_LESSON).append(lesson.getLessonType()).append("\n")
                    .append(Constants.TYPE_WEEK).append(lesson.getTypeWeek()).append("\n")
                    .append("==================").append("\n");
        });
        return message.toString();
    }
}
