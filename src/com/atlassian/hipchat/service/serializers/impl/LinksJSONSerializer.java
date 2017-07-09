package com.atlassian.hipchat.service.serializers.impl;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atlassian.hipchat.service.serializers.JSONSerializer;
import org.apache.commons.lang3.tuple.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class LinksJSONSerializer implements JSONSerializer {
    @Override
    public Pair<String, JSONArray> serialize(List<String> links) throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (String link : links) {
            jsonArray.put(new JSONObject().put("url", link).put("title", getTitle(link)));
        }
        return Pair.of("links", jsonArray);
    }

    private String getTitle(String link) throws IOException {
        Request request = new Request.Builder()
              .url(link)
              .build();

        Pattern pattern = Pattern.compile("<title>(.+?)</title>");
        Matcher matcher = pattern.matcher(new OkHttpClient().newCall(request).execute().body().string());
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
