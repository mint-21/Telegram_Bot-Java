import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Button2 {
    public synchronized void setButtons2(ReplyKeyboardMarkup replyKeyboardMarkup) {

// Создаем клавиуатуру


// Создаем список строк клавиатуры
        List<KeyboardRow> keyboard2 = new ArrayList<>();

// Создаем строчки клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThreeRow = new KeyboardRow();

// Добавляем кнопки в строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Москва"));
        keyboardSecondRow.add(new KeyboardButton("Санкт-Петербург"));
        keyboardSecondRow.add(new KeyboardButton("Другой город"));

// Добавляем все строчки клавиатуры в список
        keyboard2.add(keyboardFirstRow);
        keyboard2.add(keyboardSecondRow);
        keyboard2.add(keyboardThreeRow);

// и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard2);
    }
}
