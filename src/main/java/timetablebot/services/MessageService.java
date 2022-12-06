package timetablebot.services;

public interface MessageService {

    /**
     * Метод создания сообщения по полученной команде пользователя
     *
     * @param command Комманда пользователя
     * @return {@link String} Сформированное сообщение
     */
    String createMessageByCommand(String command);

    /**
     * Метод создания сообщения по полученном сообщеннию пользователя
     *
     * @param message Полученное сообщение пользователя
     * @return {@link String} Сформированное сообщение
     */
    String createMessageByMessage(String message);
}
