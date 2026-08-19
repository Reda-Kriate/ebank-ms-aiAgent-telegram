package org.reda.chatbotservice.telegram;

import jakarta.annotation.PostConstruct;
import org.reda.chatbotservice.service.AiAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.api.token}")
    private String botToken;
    private AiAgent aiAgent;

    public TelegramBot(AiAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    @PostConstruct
    public void registerTelegramBot(){
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpdateReceived(Update telegramRequest) {
        try {
            if(!telegramRequest.hasMessage()) return;
            String message = telegramRequest.getMessage().getText();
            Long chatId = telegramRequest.getMessage().getChatId();
            String answer = aiAgent.askAgent(chatId.toString(), message);
            sendTextMessage(chatId, answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "REDA_EBANK_BOT";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private void sendTextMessage(Long chatId, String message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(chatId.toString(), message);
        execute(sendMessage);
    }
}
