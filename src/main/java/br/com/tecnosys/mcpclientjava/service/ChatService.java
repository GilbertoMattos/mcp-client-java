package br.com.tecnosys.mcpclientjava.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatService {

    private final ChatClient chatClient;
    String systemText = """
            Você é um assistente que SÓ pode usar as ferramentas fornecidas para responder.
            Se uma pergunta não puder ser respondida usando as ferramentas disponíveis,
            informe que você não pode responder à pergunta.
            NÃO tente responder com seu conhecimento geral.
            Avalie a pergunta do usuário e determine qual ferramenta, se houver,
            pode ser usada para respondê-la.
            """;

    public ChatService(ChatClient.Builder chatClientBuilder,
                       ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(tools)
                .build();
    }


    public String chat(String userMessage) {
        return chatClient
                .prompt()
//                .system(systemText)
                .user(userMessage)
                .call()
                .content();
    }
}

