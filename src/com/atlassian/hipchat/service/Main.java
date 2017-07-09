package com.atlassian.hipchat.service;

import com.atlassian.hipchat.service.module.ChatServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector(new ChatServiceModule());
        ChatService chatService = injector.getInstance(ChatService.class);
        System.out.println(chatService.getChatContents("some text @chris @matt some text (megusta) (coffee) some" +
                " text http://www.baeldung.com/guide-to-okhttp"));
    }
}
