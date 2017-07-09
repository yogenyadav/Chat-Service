package com.atlassian.hipchat.service.serializers;

import java.io.IOException;

import org.apache.commons.lang3.tuple.Pair;

/**
 * MessageSerializer interface
 *
 * @param <I>
 * @param <O>
 */
public interface MessageSerializer<I, O> {
    Pair<String, O> serialize(I message) throws IOException;
}
