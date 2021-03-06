import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Button {

    public synchronized void setButtons(ReplyKeyboardMarkup replyKeyboardMarkup) {

// Создаем клавиуатуру


// Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

// Создаем строчки клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        KeyboardRow keyboardThreeRow = new KeyboardRow();

// Добавляем кнопки в строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardSecondRow.add(new KeyboardButton("/settings"));
        keyboardThreeRow.add(new KeyboardButton("Weather"));

// Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThreeRow);

// и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
