package com.atlassian.hipchat.service.parsers.impl;

import com.atlassian.hipchat.service.parsers.MessageParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LinksParser.
 *
 */
public class LinksParser implements MessageParser {
    private static final String LINKS_REGEX = "(https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";

    @Override
    public List<String> parse(final String message) {
        Pattern p = Pattern.compile(LINKS_REGEX, Pattern.CASE_INSENSITIVE);
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
