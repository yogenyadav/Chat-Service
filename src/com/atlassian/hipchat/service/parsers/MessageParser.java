package com.atlassian.hipchat.service.parsers;

import java.util.List;

/**
 * Message Parser interface.
 *
 */
public interface MessageParser {
    List<String> parse(String message);
}
