package org.reda.chatbotservice.controller;

import org.reda.chatbotservice.service.AiAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChatBotController {

    private final AiAgent aiAgent;

    public ChatBotController(AiAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(@RequestParam(name = "id") String conversationId ,
                       @RequestParam(name = "query",defaultValue = "Bonjour") String message
                       ) {

        return aiAgent.askAgent(conversationId, message);
    }
}
