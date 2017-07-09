package com.atlassian.hipchat.service.serializers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class LinksJSONSerializerTest {

    @Test
    public void testSuccess() throws IOException {
        LinksJSONSerializer serializer = new LinksJSONSerializer();
        Pair<String, JSONArray> p = serializer.serialize(ImmutableList.of("http://www.baeldung.com/guide-to-okhttp"));
        assertTrue(p.getRight().similar(getExpected()));
        assertEquals("links", p.getLeft());
    }

    private JSONArray getExpected() {
        return new JSONArray().put(new JSONObject().put("url", "http://www.baeldung" +
              ".com/guide-to-okhttp").put("title", "A Guide to OkHttp | Baeldung"));
    }
}
