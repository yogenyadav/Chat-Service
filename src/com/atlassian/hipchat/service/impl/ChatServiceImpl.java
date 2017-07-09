package com.atlassian.hipchat.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import com.atlassian.hipchat.service.ChatService;
import com.atlassian.hipchat.service.parsers.MessageParser;
import com.atlassian.hipchat.service.serializers.MessageSerializer;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ChatServiceImpl.
 *
 */
public class ChatServiceImpl implements ChatService {

    private final Map<MessageParser, MessageSerializer> parsers;

    @Inject
    public ChatServiceImpl(Map<MessageParser, MessageSerializer> parsers) {
        this.parsers = parsers;
    }

    @Override
    public JSONObject getChatContents(String message) throws IOException {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<MessageParser, MessageSerializer> entry : this.parsers.entrySet()) {
            Pair<String, JSONArray> pair = entry.getValue().serialize(entry.getKey().parse(message));
            jsonObject.put(pair.getLeft(), pair.getRight());
        }
        return jsonObject;
    }
}
