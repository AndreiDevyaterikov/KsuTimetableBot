package timetablebot.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;


@Component
@Getter
public class BotConfig {

    @Value("${telegram.token}")
    private String botToken;

    @Value("${telegram.name}")
    private String botUsername;

    public SetMyCommands getBotCommands(){
        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(List.of(
                new BotCommand("/faculties", "Список всех институтов"),
                new BotCommand("/lesson_group_today", "Расписание группы на сегодня")
        ));
        return setMyCommands;
    }
}
