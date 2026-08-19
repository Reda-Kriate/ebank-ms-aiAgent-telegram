package org.reda.chatbotservice.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class AiAgent {

    private final ChatClient chatClient;

    public AiAgent(ChatClient.Builder chatClient,
                             ChatMemory chatMemory,
                             ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }

    public String askAgent(String conversationId, String query) {
        return chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .user(query)
                .call()
                .content();
    }

}
