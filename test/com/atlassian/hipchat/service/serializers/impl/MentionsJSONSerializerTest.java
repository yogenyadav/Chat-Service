package com.atlassian.hipchat.service.serializers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class MentionsJSONSerializerTest {

    @Test
    public void testSuccess() {
        MentionsJSONSerializer serializer = new MentionsJSONSerializer();
        Pair<String, JSONArray> p = serializer.serialize(ImmutableList.of("chris", "matt"));
        assertTrue(p.getRight().similar(getExpected()));
        assertEquals("mentions", p.getLeft());
    }

    private JSONArray getExpected() {
        return new JSONArray().put("chris").put("matt");
    }
}
