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
                    try {
                        execute(sendMessage.setText("Чем могу помочь?"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/settings":
                    try {
                        execute(sendMessage.setText("Что настраиваем?"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Другой город":
                    switch (massage.getText()) {
                        case "Другой город":
                            try {
                                execute(sendMessage.setText("Введите название города"));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            try {
                                execute(sendMessage.setText("Не найден"));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                    }
                    break;
                case "Weather":
                    Button2 button2 = new Button2();
                    button2.setButtons2(replyKeyboardMarkup);
                    switch (massage.getText()) {
                        case "Weather":
                            try {
                                execute(sendMessage.setText("В каком городе хотите узнать погоду?"));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            try {
                                execute(sendMessage.setText("Введите другую команду"));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                    }
                    break;
                default:
                    try {
                        String text = Weather.getWeather(massage.getText(), model);
                        try {
                            execute(sendMessage.setText(text));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        try {
                            execute(sendMessage.setText("Город не найден"));
                        } catch (TelegramApiException ex) {
                            ex.printStackTrace();
                        }
                    }
            }
        }
    }
}
