package timetablebot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import timetablebot.makers.TextMaker;

@Component
@AllArgsConstructor
public class MessageHandler {

    private final TextMaker textMaker;

    public SendMessage getMessage(Message message, String textMessage) {


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(textMaker.getText(textMessage));

        return sendMessage;
    }
}
