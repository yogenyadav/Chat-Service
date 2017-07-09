package com.atlassian.hipchat.service.parsers.impl;

import com.atlassian.hipchat.service.parsers.MessageParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MentionsParser.
 */
public class MentionsParser implements MessageParser {
    private static final String MENTIONS_REGEX = "@(.+?) ";

    @Override
    public List<String> parse(final String message) {
        Pattern p = Pattern.compile(MENTIONS_REGEX);
        Matcher m = p.matcher(message);
        List<String> mentions = new ArrayList<>();
        while (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                mentions.add(m.group(i));
            }
        }
        return mentions;
    }
}
