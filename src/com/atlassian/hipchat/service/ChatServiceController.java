package com.atlassian.hipchat.service;

import com.atlassian.hipchat.service.module.ChatServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ChatServiceController {
    private final ChatService chatService;

    public ChatServiceController() {
        Injector injector = Guice.createInjector(new ChatServiceModule());
        chatService = injector.getInstance(ChatService.class);
    }

    @RequestMapping(value = "chat/contents", method = RequestMethod.GET)
    public String getChatContents(@RequestParam(name = "message") String message) {
        try {
            System.out.println(message);
            return chatService.getChatContents(message).toString();
        } catch (IOException e) {
            throw new RuntimeException("Internal error", e);
        }
    }
    
}
