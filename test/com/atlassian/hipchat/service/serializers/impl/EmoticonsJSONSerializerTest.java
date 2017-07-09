package com.atlassian.hipchat.service.serializers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class EmoticonsJSONSerializerTest {

    @Test
    public void testSuccess() {
        EmoticonsJSONSerializer serializer = new EmoticonsJSONSerializer();
        Pair<String, JSONArray> p = serializer.serialize(ImmutableList.of("megusta", "coffee"));
        assertTrue(p.getRight().similar(getExpected()));
        assertEquals("emoticons", p.getLeft());
    }

    private JSONArray getExpected() {
        return new JSONArray().put("megusta").put("coffee");
    }
}
