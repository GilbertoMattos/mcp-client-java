package br.com.tecnosys.mcpclientjava.config;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ToolsConfig {

    private final List<McpSyncClient> mcpSyncClients;

    public ToolsConfig(List<McpSyncClient> mcpSyncClients) {
        this.mcpSyncClients = mcpSyncClients;
    }

    @Bean
    public ChatClient anthropicChatClient(AnthropicChatModel chatModel) {
        return ChatClient.create(chatModel);
    }

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(5)
                .build();
    }

    @Bean
    public ChatClient chatClient(AnthropicChatModel chatModel) {
        // Obtém todas as ferramentas disponíveis do Server MCP
        SyncMcpToolCallbackProvider provider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        ToolCallback[] allTools = provider.getToolCallbacks();

        // Configura o ChatClient com o modelo Anthropic e ferramentas habilitadas
        return ChatClient.builder(chatModel)
                .defaultToolCallbacks(allTools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory()).build())
                .build();
    }
}
