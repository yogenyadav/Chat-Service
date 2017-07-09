package com.atlassian.hipchat.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.atlassian.hipchat.service.parsers.impl.EmoticonsParser;
import com.atlassian.hipchat.service.parsers.impl.LinksParser;
import com.atlassian.hipchat.service.parsers.impl.MentionsParser;
import com.atlassian.hipchat.service.serializers.impl.EmoticonsJSONSerializer;
import com.atlassian.hipchat.service.serializers.impl.LinksJSONSerializer;
import com.atlassian.hipchat.service.serializers.impl.MentionsJSONSerializer;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@RunWith(MockitoJUnitRunner.class)
public class ChatServiceImplTest {

    @Mock
    LinksJSONSerializer linksJSONSerializer;
    @Mock
    MentionsJSONSerializer mentionsJSONSerializer;
    @Mock
    EmoticonsJSONSerializer emoticonsJSONSerializer;

    @Mock
    LinksParser linksParser;
    @Mock
    MentionsParser mentionsParser;
    @Mock
    EmoticonsParser emoticonsParser;

    @Test
    public void testSuccess() throws IOException {
        when(linksJSONSerializer.serialize(anyObject())).thenReturn(Pair.of("links", new JSONArray().put(new
              JSONObject().put("url", "http://www.baeldung" +
              ".com/guide-to-okhttp").put("title", "A Guide to OkHttp | Baeldung"))));
        when(mentionsJSONSerializer.serialize(anyObject())).thenReturn(Pair.of("mentions", new JSONArray().put
              ("chris").put("matt")));
        when(emoticonsJSONSerializer.serialize(anyObject())).thenReturn(Pair.of("emoticons", new JSONArray().put
              ("megusta").put("coffee")));
        when(linksParser.parse(anyObject())).thenReturn(ImmutableList.of("http://www.baeldung.com/guide-to-okhttp"));
        when(mentionsParser.parse(anyObject())).thenReturn(ImmutableList.of("name1", "name2"));
        when(emoticonsParser.parse(anyObject())).thenReturn(ImmutableList.of("text1", "text2"));

        ChatServiceImpl chatService = new ChatServiceImpl(
              ImmutableMap.of(
                    linksParser, linksJSONSerializer,
                    mentionsParser, mentionsJSONSerializer,
                    emoticonsParser, emoticonsJSONSerializer
              )
        );
        JSONObject jsonObject = chatService.getChatContents("some text @chris @matt some text (megusta) (coffee) some" +
              " text http://www.baeldung.com/guide-to-okhttp");
        assertTrue(jsonObject.similar(getExpected()));
    }

    @Test(expected = NullPointerException.class)
    public void testInputValidation() throws IOException {
        ChatServiceImpl chatService = new ChatServiceImpl(
                ImmutableMap.of(
                        linksParser, linksJSONSerializer,
                        mentionsParser, mentionsJSONSerializer,
                        emoticonsParser, emoticonsJSONSerializer
                )
        );
        chatService.getChatContents(null);
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
