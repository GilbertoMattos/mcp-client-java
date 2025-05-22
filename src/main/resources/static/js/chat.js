// Chat functionality
const input = document.getElementById("user-input");
const chatBox = document.getElementById("chat-box");
const sendButton = document.getElementById("send-button");

// Function to format current time
function getCurrentTime() {
    const now = new Date();
    return now.getHours().toString().padStart(2, '0') + ':' + 
           now.getMinutes().toString().padStart(2, '0');
}

// Function to add a message to the chat
function addMessage(text, isUser) {
    const messageDiv = document.createElement('div');
    messageDiv.className = isUser ? 'message user-message' : 'message ai-message';

    const currentTime = getCurrentTime();

    // Create content div to hold the message text
    const contentDiv = document.createElement('div');
    contentDiv.className = 'message-content';

    if (isUser) {
        // For user messages, use textContent to prevent HTML injection
        contentDiv.textContent = text;
    } else {
        // For AI messages, use innerHTML to render HTML content
        contentDiv.innerHTML = text;
    }

    messageDiv.appendChild(contentDiv);

    // Add timestamp
    const timeSpan = document.createElement('span');
    timeSpan.className = 'message-time';
    timeSpan.textContent = currentTime;
    messageDiv.appendChild(timeSpan);

    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// Function to show typing indicator
function showTypingIndicator() {
    const indicator = document.createElement('div');
    indicator.className = 'typing-indicator';
    indicator.id = 'typing-indicator';
    indicator.innerHTML = '<span></span><span></span><span></span>';
    chatBox.appendChild(indicator);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// Function to remove typing indicator
function removeTypingIndicator() {
    const indicator = document.getElementById('typing-indicator');
    if (indicator) {
        indicator.remove();
    }
}

// Function to send message
function sendMessage() {
    const msg = input.value.trim();
    if (msg === '') return;

    addMessage(msg, true);
    input.value = "";

    showTypingIndicator();

    fetch("/api/chat", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({message: msg})
    })
    .then(resp => resp.text())
    .then(resp => {
        removeTypingIndicator();
        addMessage(resp, false);
    })
    .catch(error => {
        removeTypingIndicator();
        addMessage("Desculpe, ocorreu um erro ao processar sua mensagem.", false);
        console.error("Error:", error);
    });
}

// Event listeners
input.addEventListener("keypress", function(e) {
    if (e.key === "Enter") {
        sendMessage();
    }
});

sendButton.addEventListener("click", sendMessage);

// Focus input on page load
window.onload = function() {
    input.focus();
};