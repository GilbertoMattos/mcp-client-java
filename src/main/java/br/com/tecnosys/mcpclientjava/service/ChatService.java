package br.com.tecnosys.mcpclientjava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder,
                       ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(tools)
                .build();
    }


    public String chat(String message) {
        return chatClient.prompt(message).call().content();
    }
}

