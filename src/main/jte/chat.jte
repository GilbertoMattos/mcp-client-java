@param String title

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>@title</title>
    <link rel="stylesheet" href="/css/chat.css">
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
</head>
<body>
    <header>
        <h1>@title</h1>
    </header>

    <div class="container">
        <div class="chat-container">
            <div id="chat-box">
                <!-- Messages will appear here -->
            </div>

            <div class="input-container">
                <textarea id="user-input" placeholder="Digite sua mensagem..." rows="1" autocomplete="off"></textarea>
                <button id="send-button">Enviar</button>
            </div>
        </div>
    </div>

    <script src="/js/chat.js"></script>
</body>
</html>
