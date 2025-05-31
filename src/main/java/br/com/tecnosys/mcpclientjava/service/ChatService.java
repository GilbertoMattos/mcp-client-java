package br.com.tecnosys.mcpclientjava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatService {

    String conversationId = "007";
    String systemText = """
            Quando apropriado, use formatação Markdown em suas respostas para melhorar a legibilidade.
            """;

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userMessage) {
        return chatClient
                .prompt()
                .tools()
                .system(systemText)
                .user(userMessage)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
