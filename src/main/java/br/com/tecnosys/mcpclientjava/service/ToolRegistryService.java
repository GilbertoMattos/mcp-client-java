package br.com.tecnosys.mcpclientjava.service;

import io.modelcontextprotocol.server.McpServerFeatures;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ToolRegistryService {

    private final List<SyncMcpToolCallbackProvider> toolCallbackProviders;
    private final Map<String, Boolean> toolStatusMap = new ConcurrentHashMap<>();

    public ToolRegistryService(List<SyncMcpToolCallbackProvider> toolCallbackProviders) {
        this.toolCallbackProviders = toolCallbackProviders;
    }

    public void setToolEnabled(String toolName, boolean enabled) {
        toolStatusMap.put(toolName, enabled);
    }

    public boolean isToolEnabled(String toolName) {
        return toolStatusMap.getOrDefault(toolName, false);
    }

    public boolean isEnabled(ToolCallback callback) {
        return isToolEnabled(callback.getToolDefinition().name());
    }

    public List<McpServerFeatures.SyncToolSpecification> listFeatures() {
        return toolCallbackProviders.stream()
                .flatMap(p -> Arrays.stream(p.getToolCallbacks()))
                .map(McpToolUtils::toSyncToolSpecification)
                .toList();
    }
}
