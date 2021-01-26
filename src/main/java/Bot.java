import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.contracts.GamesService;
import services.impls.GamesServiceImpl;

import java.io.IOException;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {


    private final GamesService service = new GamesServiceImpl();
    private final Properties properties = new Properties();

    private String BOT_USERNAME;
    private String BOT_TOKEN;

    {
        try {
            properties.load(Bot.class.getResourceAsStream("/bot.properties"));
            BOT_USERNAME = properties.getProperty("botname");
            BOT_TOKEN = properties.getProperty("token");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String req = message.getText();

        if("all".equalsIgnoreCase(req)){
            sendResponse(message.getChatId(), 100);
            return;
        }

        if (req.isEmpty()){
            sendResponse(message.getChatId(),-1);
            return;
        }

        try {
            sendResponse(message.getChatId(), Integer.parseInt(message.getText()));
        } catch (NumberFormatException exc){
            sendResponse(message.getChatId(),-1);
        }
    }

    private void sendResponse(Long chatId, int msg){
        SendMessage response = new SendMessage();

        try {
            response.setChatId(chatId.toString());

            if (msg != -1) {
                response.setText(service.getGamesList(msg));
            } else {
                response.setText("Incorrect input");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
