package timetablebot.constants;

import timetablebot.enums.Emojis;

public class Messages {
    public static final String HELLO_MESSAGE = "Привет, %s %s " + Emojis.WAVE_HAND.getEmojiCode();
    public static final String UNKNOWN_COMMAND = "Я не знаю такой команды " + Emojis.NO_MONTH.getEmojiCode();
    public static final String ENTER_GROUP_NAME = "Введите название группы";
    public static final String UNKNOWN_GROUP_NAME = "Группы с названием %s не существует";
    public static final String NOT_SELECTED_COMMAND = "Не выбрана комманда, выберите комманду из списка меню " +
            Emojis.EXCLAMATION_MARK.getEmojiCode();
}
