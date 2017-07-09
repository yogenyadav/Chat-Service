package com.atlassian.hipchat.service.serializers.impl;

import java.util.List;

import com.atlassian.hipchat.service.serializers.JSONSerializer;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;

/**
 * EmoticonsJSONSerializer.
 *
 */
public class EmoticonsJSONSerializer implements JSONSerializer {
    @Override
    public Pair<String, JSONArray> serialize(final List<String> emoticons) {
        JSONArray jsonArray = new JSONArray();
        for (String emoticon : emoticons) {
            jsonArray.put(emoticon);
        }
        return Pair.of("emoticons", jsonArray);
    }
}
