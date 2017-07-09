package com.atlassian.hipchat.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.atlassian.hipchat.service.ChatService;
import com.atlassian.hipchat.service.module.ChatServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.json.JSONObject;

import java.io.IOException;

public class ChatServiceLambda {
    public JSONObject handler(String message, Context context) {
        Injector injector = Guice.createInjector(new ChatServiceModule());
        ChatService chatService = injector.getInstance(ChatService.class);
        try {
            return chatService.getChatContents(message);
        } catch (IOException e) {
            throw new RuntimeException("Internal error", e);
        }
    }
}
