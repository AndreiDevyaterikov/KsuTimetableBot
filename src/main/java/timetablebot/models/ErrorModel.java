package timetablebot.models;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ErrorModel {
    private Integer statusCode;
    private String description;
}
