package org.reda.chatbotservice.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
public class ChatBotController {
    private final ChatClient chatClient;

    public ChatBotController(ChatClient.Builder chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "id") String conversationId ,
                       @RequestParam(name = "query",defaultValue = "Bonjour") String message
                       ) {
        return chatClient.prompt()
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId))
                .user(message)
                .call()
                .content();

    }
}
