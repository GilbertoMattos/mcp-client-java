package br.com.tecnosys.mcpclientjava.controller;

import br.com.tecnosys.mcpclientjava.service.ToolRegistryService;
import io.modelcontextprotocol.server.McpServerFeatures;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
public class ToolApiConfigController {

    private final ToolRegistryService toolRegistryService;

    public ToolApiConfigController(ToolRegistryService toolRegistryService) {
        this.toolRegistryService = toolRegistryService;
    }

    @GetMapping
    public List<McpServerFeatures.SyncToolSpecification> listTools() {
        return toolRegistryService.listFeatures();
    }

    @GetMapping("enabled")
    public List<String> listEnabled() {
        return toolRegistryService.listFeatures().stream()
                .map(f -> f.tool().name())
                .filter(toolRegistryService::isToolEnabled)
                .toList();
    }

    @PostMapping("/{toolName}/enable")
    public void enableTool(@PathVariable String toolName, @RequestParam boolean enabled) {
        toolRegistryService.setToolEnabled(toolName, enabled);
    }
}
