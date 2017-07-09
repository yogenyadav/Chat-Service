package com.atlassian.hipchat.service.parsers.impl;

import com.atlassian.hipchat.service.parsers.MessageParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmoticonsParser.
 *
 */
public class EmoticonsParser implements MessageParser {
    private static final String EMOTICONS_REGEX = "\\(([^)]+)\\)";

    @Override
    public List<String> parse(final String message) {
        Pattern p = Pattern.compile(EMOTICONS_REGEX);
        Matcher m = p.matcher(message);
        List<String> mentions = new ArrayList<>();
        while (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                String emoticon = m.group(i);
                if (emoticon.length() > 15) {
                    throw new IllegalArgumentException(String.format("%s is more than 15 char long", emoticon));
                }
                mentions.add(m.group(i));
            }
        }
        return mentions;
    }
}
