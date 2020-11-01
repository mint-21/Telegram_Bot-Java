import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
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

    public void messageText(SendMessage sendMessage, String text) {
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

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(massage.getChatId().toString());
        sendMessage.setReplyToMessageId(massage.getMessageId());
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        Button button = new Button();
        button.setButtons(replyKeyboardMarkup);

        if (massage != null && massage.hasText()) {
            switch (massage.getText()){
                case "/help":
                    messageText(sendMessage, "Чем могу помочь?");
                    break;
                case "/settings":
                    messageText(sendMessage, "Что настраиваем?");
                    break;
                case "Другой город":
                    switch (massage.getText()) {
                        case "Другой город":
                            messageText(sendMessage, "Введите название города");
                            break;
                        default:
                            messageText(sendMessage, "Не найден");
                    }
                    break;
                case "Weather":
                    Button2 button2 = new Button2();
                    button2.setButtons2(replyKeyboardMarkup);
                    switch (massage.getText()) {
                        case "Weather":
                            messageText(sendMessage, "В каком городе хотите узнать погоду?");
                            break;
                        default:
                            messageText(sendMessage, "Введите другую команду");
                    }
                    break;
                default:
                    try {
                        String text = Weather.getWeather(massage.getText(), model);
                        messageText(sendMessage, text);
                    } catch (IOException e) {
                        messageText(sendMessage, "Город не найден");
                    }
            }
        }
    }
}
