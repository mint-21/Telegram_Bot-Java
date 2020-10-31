import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.io.IOException;

public class Example extends TelegramLongPollingBot{
    private static final String token = "1279378858:AAEpEE-_SBQg4LJKbeIGyzBAusy5Jt1jBUQ";
    private static final String useName = "BookingFirst_Bot";

    @Override
    public String getBotUsername() {
        return useName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        Button button = new Button();
        button.setButtons(sendMessage);
        try {
            execute(sendMessage.setText(text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message massage = update.getMessage();
        if (massage != null && massage.hasText()) {
            switch (massage.getText()){
                case "/help":
                    sendMsg(massage, "Чем могу помочь?");
                    break;
                case "/settings":
                    sendMsg(massage, "Что настраиваем?");
                    break;
                case "/output":
                    sendMsg(massage, "До встречи!");
                    break;
                default:
                    try {
                        String text = Weather.getWeather(massage.getText(), model);
                        sendMsg(massage, text);
                    } catch (IOException e) {
                        sendMsg(massage, "Город не найден");
                    }
            }
        }
    }
}
