package timetablebot;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import timetablebot.configs.BotConfig;
import timetablebot.constants.Command;
import timetablebot.constants.Messages;
import timetablebot.services.MessageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final MessageService messageService;
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

        var message = update.getMessage();
        var chatId = message.getChatId();

        if (message.isCommand()) {
            if (Command.START.getCommand().equals(message.getText())) {
                createHelloMessage(message.getFrom(), chatId);
            } else {
                if (update.hasMessage()) {
                    var textMessage = messageService.createMessageByCommand(message.getText());
                    sendMessage(chatId, textMessage);
                }
            }
        } else {
            if (update.hasMessage()) {
                var textMessage = messageService.createMessageByMessage(message.getText());
                if (StringUtil.isNullOrEmpty(textMessage)) {
                    sendMessage(chatId, Messages.NOT_SELECTED_COMMAND);
                } else {
                    sendMessage(chatId, textMessage);
                }
            }
        }
    }

    private void createHelloMessage(User user, Long chatId) {
        var textMessage = String.format(Messages.HELLO_MESSAGE, user.getFirstName(), user.getLastName());
        try {
            execute(botConfig.getBotCommands());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        sendMessage(chatId, textMessage);
    }

    private void sendMessage(Long chatId, String textMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
