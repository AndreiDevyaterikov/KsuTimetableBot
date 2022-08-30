package timetablebot.enums;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Emojis {
    //https://emojipedia.org/

    GRINNING_FACE(EmojiParser.parseToUnicode(":grinning:")),
    ALARM_CLOCK(EmojiParser.parseToUnicode(":alarm_clock:")),
    SMILING_FACE(EmojiParser.parseToUnicode(":relaxed:")),
    NO_MONTH(EmojiParser.parseToUnicode(":no_mouth:")),
    SCROLL(EmojiParser.parseToUnicode(":scroll:")),
    PAGE_FACING_UP(EmojiParser.parseToUnicode(":page_facing_up:")),
    WAVE_HAND(EmojiParser.parseToUnicode(":wave:"));
    private final String emojiCode;
}
