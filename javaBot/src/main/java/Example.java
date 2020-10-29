import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Example extends TelegramLongPollingBot{
    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Example());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "Booking_Bot";
        //возвращаем юзера
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message massage = update.getMessage();
        if (massage != null && massage.hasText()) {
            switch (massage.getText()){
                case "/help":
                    sendMsg(massage, "Чем могу помочь?");
                    break;
                case "/setting":
                    sendMsg(massage, "Что настраиваем?");
                default:
                    throw new IllegalStateException("Unexpected value: " + massage.getText());
            }
        }
    }

    @Override
    public String getBotToken() {
        return "1279378858:AAEpEE-_SBQg4LJKbeIGyzBAusy5Jt1jBUQ";
        //Токен бота
    }

}
