package timetablebot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import timetablebot.configs.BotConfig;
import timetablebot.enums.Emojis;
import timetablebot.handlers.MessageHandler;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final MessageHandler messageHandler;
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.getMessage().getText().equals("/start")) {

            try {
                execute(botConfig.getBotCommands());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

            SendMessage helloMessage = new SendMessage();
            helloMessage.setChatId(update.getMessage().getChatId());
            var user = update.getMessage().getFrom();
            helloMessage.setText("Привет, " + user.getFirstName() + " " + user.getLastName() + " " + Emojis.WAVE_HAND.getEmojiCode());

            try {
                execute(helloMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else {

            if (update.hasMessage()) {
                var message = messageHandler.getMessage(update.getMessage(), update.getMessage().getText());
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
