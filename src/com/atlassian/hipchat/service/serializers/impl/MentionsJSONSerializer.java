package com.atlassian.hipchat.service.serializers.impl;

import java.util.List;

import com.atlassian.hipchat.service.serializers.JSONSerializer;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;

/**
 * MentionsJSONSerializer.
 *
 */
public class MentionsJSONSerializer implements JSONSerializer {
    @Override
    public Pair<String, JSONArray> serialize(final List<String> mentions) {
        JSONArray jsonArray = new JSONArray();
        for (String mention : mentions) {
            jsonArray.put(mention);
        }
        return Pair.of("mentions", jsonArray);
    }
}
