package com.atlassian.hipchat.service.module;

import com.atlassian.hipchat.service.ChatService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ChatServiceModuleTest {

    @Test
    public void testSuccess() throws IOException {
        Injector injector = Guice.createInjector(new ChatServiceModule());
        ChatService chatService = injector.getInstance(ChatService.class);
        JSONObject jsonObject = chatService.getChatContents("some text @chris @matt some text (megusta) (coffee) some" +
                " text http://www.baeldung.com/guide-to-okhttp");
        assertTrue(jsonObject.similar(getExpected()));
    }

    private JSONObject getExpected() {
        return new JSONObject()
                .put("mentions", new JSONArray().put("chris").put("matt"))
                .put("emoticons", new JSONArray().put("megusta").put("coffee"))
                .put("links", new JSONArray()
                        .put(new JSONObject().put("url", "http://www.baeldung.com/guide-to-okhttp")
                                .put("title", "A Guide to OkHttp | Baeldung")));
    }
}
