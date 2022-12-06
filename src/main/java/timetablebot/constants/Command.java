package timetablebot.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {

    START("/start", null),
    GET_FACULTIES ("/faculties", "Список всех институтов"),
    LESSONS_FOR_GROUP_TODAY("/lessons_for_group_today", "Расписание группы на сегодня");

    private final String command;
    private final String description;
}
