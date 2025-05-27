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
- Docker e Docker Compose (para executar o Open WebUI)

## Docker Compose para Open WebUI

Este projeto inclui um arquivo `docker-compose.yml` para facilitar a instalação e execução do Open WebUI, uma interface web para interagir com LLMs.

### Pré-requisitos

- Docker
- Docker Compose

### Como executar o Open WebUI

1. Certifique-se de que o Docker e o Docker Compose estão instalados em seu sistema
2. Configure a variável de ambiente `OPEN_AI_API_KEY` com sua chave de API do OpenAI:
   ```bash
   export OPEN_AI_API_KEY=sua-chave-api-openai
   ```
3. No diretório raiz do projeto, execute:
   ```bash
   docker-compose up -d
   ```
4. Acesse o Open WebUI em `http://localhost:3000`

### Configurações adicionais

O arquivo `docker-compose.yml` inclui:

- Configuração básica do Open WebUI
- Persistência de dados usando volumes Docker
- Configuração opcional para banco de dados PostgreSQL (comentada por padrão)

Para habilitar o banco de dados PostgreSQL ou modificar outras configurações, edite o arquivo `docker-compose.yml` conforme necessário.

Para parar os contêineres:
```bash
docker-compose down
```

Para remover os volumes e dados persistentes:
```bash
docker-compose down -v
```
