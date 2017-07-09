package com.atlassian.hipchat.service.serializers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;

/**
 * JSON Serializer interface.
 *
 */
public interface JSONSerializer extends MessageSerializer<List<String>, JSONArray> {
    Pair<String, JSONArray> serialize(List<String> message) throws IOException;
}
