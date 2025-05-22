package br.com.tecnosys.mcpclientjava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatService {

    String conversationId = "007";
    private final ChatClient chatClient;
    String systemText = """
            Quando apropriado, use formatação HTML em suas respostas para melhorar a legibilidade:
            - Use <b>texto</b> para texto em negrito
            - Use <i>texto</i> para texto em itálico
            - Use <code>código</code> para trechos curtos de código
            - Use <pre><code>código</code></pre> para blocos de código
            - Use <ul><li>item</li></ul> para listas não ordenadas
            - Use <ol><li>item</li></ol> para listas ordenadas
            - Use <table><tr><th>cabeçalho</th></tr><tr><td>dado</td></tr></table> para tabelas
            - Use <a href="url">texto</a> para links
            """;

    public ChatService(ChatClient.Builder chatClientBuilder,
                       ToolCallbackProvider tools, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(tools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
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
