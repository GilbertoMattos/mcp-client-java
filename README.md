# MCP Client Java

Este é um cliente MCP (Model Context Protocol) que permite aos usuários realizar solicitações via texto e processar essas solicitações usando uma LLM (Large Language Model) através de um servidor MCP.

## Funcionalidades

- Interface moderna para solicitações via texto
- Processamento de texto usando LLM através do servidor MCP
- Respostas em tempo real com indicador de carregamento
- Design responsivo que funciona em diferentes dispositivos

## Tecnologias Utilizadas

- Spring Boot 3.4.5
- Spring AI (MCP Client)
- JTE (Java Template Engine)
- HTML/CSS/JavaScript

## Configuração

A aplicação está configurada para se conectar a um servidor MCP em `http://localhost:8080`. Você pode alterar esta configuração no arquivo `application.properties`:

```properties
spring.ai.mcp.client.base-url=http://localhost:8080
spring.ai.mcp.client.default-options.temperature=0.7
spring.ai.mcp.client.default-options.max-tokens=2000
spring.ai.mcp.client.default-options.model=gpt-3.5-turbo
```

A aplicação roda na porta 8081 para evitar conflitos com o servidor MCP:

```properties
server.port=8081
```

## Executando a Aplicação

Para executar a aplicação, use o seguinte comando:

```bash
./mvnw spring-boot:run
```

Acesse a aplicação em `http://localhost:8081`

## Estrutura do Projeto

- `src/main/java/br/com/tecnosys/mcpclientjava/controller/ChatController.java`: Controlador para lidar com as requisições web
- `src/main/java/br/com/tecnosys/mcpclientjava/service/ChatService.java`: Serviço para processar mensagens usando o cliente MCP
- `src/main/java/br/com/tecnosys/mcpclientjava/config/JteConfig.java`: Configuração do motor de templates JTE
- `src/main/jte/index.jte`: Template JTE para a página principal
- `src/main/resources/static/css/styles.css`: Estilos CSS para a interface
- `src/main/resources/static/js/chat.js`: JavaScript para a funcionalidade do chat
- `src/main/resources/application.properties`: Configurações da aplicação

## Como Usar

1. Acesse a aplicação em `http://localhost:8081`
2. Digite sua mensagem na caixa de texto
3. Clique em "Enviar" ou pressione Enter
4. Aguarde a resposta do servidor MCP