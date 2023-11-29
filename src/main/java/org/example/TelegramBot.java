package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot extends TelegramLongPollingBot {

    static Logger logger = LogManager.getLogger(TelegramBot.class.getName());

    private final Db db;

    public TelegramBot(String telegramBotToken, Db db) {
        super(telegramBotToken);
        this.db = db;
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            logger.error(e);
        }

        logger.info("Telegram bot started!");
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                handleIncomingMessage(update.getMessage());
            } catch (TelegramApiException e) {
                logger.error(e);
            }
        }

    }

    private void handleIncomingMessage(Message message) throws TelegramApiException {
        String inputTextMessage = message.getText();

        SendMessage outputMessage = new SendMessage(); // Create a SendMessage object with mandatory fields
        outputMessage.setChatId(message.getChatId().toString());
        outputMessage.setText(Integer.toString(db.getState(765865, 76576L)));
        //outputMessage.setText("press /newGroup or enter id of existing group");
        execute(outputMessage);
        //message.enableMarkdown(true);

//            ReplyKeyboardMarkup keyboardMarkup = getReplyKeyboardMarkup();
//            // Add it to the message
//            message.setReplyMarkup(keyboardMarkup);

    }

//    private static ReplyKeyboardMarkup getReplyKeyboardMarkup() {
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        // Create the keyboard (list of keyboard rows)
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        // Create a keyboard row
//        KeyboardRow row = new KeyboardRow();
//        // Set each button, you can also use KeyboardButton objects if you need something else than text
//        row.add("Row 1 Button 1");
//        row.add("Row 1 Button 2");
//        row.add("Row 1 Button 3");
//        // Add the first row to the keyboard
//        keyboard.add(row);
//        // Create another keyboard row
//        row = new KeyboardRow();
//        // Set each button for the second line
//        row.add("Row 2 Button 1");
//        row.add("Row 2 Button 2");
//        row.add("Row 2 Button 3");
//        // Add the second row to the keyboard
//        keyboard.add(row);
//        // Set the keyboard to the markup
//        keyboardMarkup.setKeyboard(keyboard);
//        return keyboardMarkup;
//    }

    @Override
    public String getBotUsername() {
        return "Secret Santa";
    }

}
