package br.com.tecnosys.mcpclientjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatPage(Model model) {
        model.addAttribute("title", "Chat com IA");
        return "chat"; // corresponde a chat.jte
    }
}
