package com.atlassian.hipchat.service;

import java.io.IOException;

import org.json.JSONObject;

/**
 * ChatService interface.
 *
 */
public interface ChatService {
    JSONObject getChatContents(String message) throws IOException;
}
