package com.atlassian.hipchat.service.module;

import com.atlassian.hipchat.service.ChatService;
import com.atlassian.hipchat.service.impl.ChatServiceImpl;
import com.atlassian.hipchat.service.parsers.MessageParser;
import com.atlassian.hipchat.service.parsers.impl.EmoticonsParser;
import com.atlassian.hipchat.service.parsers.impl.LinksParser;
import com.atlassian.hipchat.service.parsers.impl.MentionsParser;
import com.atlassian.hipchat.service.serializers.MessageSerializer;
import com.atlassian.hipchat.service.serializers.impl.EmoticonsJSONSerializer;
import com.atlassian.hipchat.service.serializers.impl.LinksJSONSerializer;
import com.atlassian.hipchat.service.serializers.impl.MentionsJSONSerializer;
import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Singleton;
import java.util.Map;

/**
 * ChatServiceModule.
 * Guice module for Chat Service.
 */
public class ChatServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ChatService.class).to(ChatServiceImpl.class).in(Singleton.class);
    }

    @Provides
    Map<MessageParser, MessageSerializer> get() {
        return ImmutableMap.of(
                new LinksParser(), new LinksJSONSerializer(),
                new EmoticonsParser(), new EmoticonsJSONSerializer(),
                new MentionsParser(), new MentionsJSONSerializer()
        );
    }
}
