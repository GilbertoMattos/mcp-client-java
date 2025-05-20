# MCP Client Java

Este é um cliente MCP (Model Context Protocol) que permite aos usuários realizar solicitações via texto e processar essas solicitações usando uma LLM (Large Language Model) através de um servidor MCP.

## Funcionalidades

- API REST para solicitações via texto
- Processamento de texto usando LLM através do servidor MCP
- Integração com Anthropic Claude via Spring AI
- Suporte a ferramentas (tools) via ToolCallbackProvider

## Tecnologias Utilizadas

- Spring Boot 3.5.0-SNAPSHOT
- Spring AI 1.0.0-RC1 (MCP Client e Anthropic)
- Lombok
- Spring Web

## Configuração

A aplicação está configurada para se conectar a um servidor MCP em `http://localhost:8080`. Você pode alterar esta configuração no arquivo `application.yml`:

```yaml
spring:
  ai:
    anthropic:
      api-key: ${ANTHROPIC_API_KEY:your-api-key}
      chat:
        options:
          temperature: 0.8
          max-tokens: 5000
    model:
      chat: anthropic
    mcp:
      client:
        enabled: true
        name: spring-ai-mcp-client
        version: 1.0.0
        request-timeout: 60s
        type: sync
        sse:
          connections:
            server1:
              url: http://localhost:8080
              sse-endpoint: /sse
        root-change-notification: true
        toolcallback:
          enabled: true
```

A aplicação roda na porta 8088 para evitar conflitos com o servidor MCP:

```yaml
server:
  port: 8088
```

## Executando a Aplicação

Para executar a aplicação, use o seguinte comando:

```bash
./mvnw spring-boot:run
```

Acesse a API em `http://localhost:8088/chat?request=sua-mensagem`

## Estrutura do Projeto

- `src/main/java/br/com/tecnosys/mcpclientjava/McpClientJavaApplication.java`: Classe principal da aplicação
- `src/main/java/br/com/tecnosys/mcpclientjava/controller/ChatController.java`: Controlador REST para lidar com as requisições de chat
- `src/main/java/br/com/tecnosys/mcpclientjava/service/ChatService.java`: Serviço para processar mensagens usando o cliente MCP e Spring AI
- `src/main/resources/application.yml`: Configurações da aplicação

## Como Usar

1. Faça uma requisição GET para `http://localhost:8088/chat?request=sua-mensagem`
2. A API processará a mensagem usando o modelo Anthropic via MCP
3. A resposta será retornada como texto simples

## Requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- Conexão com servidor MCP (por padrão em localhost:8080)
