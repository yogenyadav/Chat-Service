package com.atlassian.hipchat.service.parsers.impl;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class LinksParserTest {

    @Test
    public void testSuccess() {
        String str = "aa aa (text1) bb bb (text2) cc cc @name1 dd dd @name2 ee ee https://stackoverflow.com/questions/5713558/detect-and-extract-url-from-a-string";
        LinksParser parser = new LinksParser();
        List<String> mentions = parser.parse(str);
        assertThat(mentions, is(ImmutableList.of("https://stackoverflow.com/questions/5713558/detect-and-extract-url-from-a-string")));
    }
}
