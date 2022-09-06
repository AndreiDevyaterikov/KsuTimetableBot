package timetablebot.models;

import lombok.Data;
import lombok.Getter;


@Data
@Getter
public class Timetable {

    private Integer id;
    private Integer lessonDay;
    private String lessonName;
    private Cabinet cabinet;
    private String lessonType;
    private User teacher;
    private String lessonTimeStart;
    private String lessonTimeEnd;
    private Integer lessonNumber;
    private Group group;
    private String typeWeek;
    private String subgroup;

}
