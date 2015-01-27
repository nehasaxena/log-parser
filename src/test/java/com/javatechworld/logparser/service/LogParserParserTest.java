package com.javatechworld.logparser.service;

import com.javatechworld.logparser.domain.LogEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: LogParserParserTest
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class LogParserParserTest {

    private LogParserParser parser = new LogParserParser();

    private static String commonLog = "10.16.177.143 - - [03/Feb/2011:13:39:10 +0000] \"GET /recipes/1223/images/1223_SMALL.jpg HTTP/1.1\" 200 6144";
    private static String combinedLog = "10.16.177.143 - - [03/Feb/2011:13:39:10 +0000] \"GET /recipes/1223/images/1223_SMALL.jpg HTTP/1.1\" 200 6144 \"http://test.com/search.do?keywords=\" \"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.237 Safari/534.10\"";
    private static String combinedLogExtraChars = "10.16.177.143 - - [03/Feb/2011:13:39:10 +0000] \"GET /recipes/1223/images/1223_SMALL.jpg HTTP/1.1\" 200 6144 \"http://test.com/search.do?keywords=\" \"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.237 Safari/534.10\"  yu";
    private static String commonLogExtra = "10.16.177.143 - - [03/Feb/2011:13:39:10 +0000] \"GET /recipes/1223/images/1223_SMALL.jpg HTTP/1.1\" 200 6144 hduje&";


    @org.junit.Test
    public void testParseForCommonLog() throws Exception {

        //Testing COMMON log format
        LogEntry result = parser.parse(commonLog);
        assert (result != null);
        assertEquals(result.getIpAddress(), "10.16.177.143");
        assertEquals(result.getIdentd(), "-");
        assertEquals(result.getDateTime(), "[03/Feb/2011:13:39:10 +0000]");
        assertNull(result.getReferer());
        assertNull(result.getUserAgent());

    }

    @org.junit.Test
    public void testParseForCombinedLog() throws Exception {

        //Testing COMBINED log format
        LogEntry result = parser.parse(combinedLog);
        assert (result != null);
        assertEquals(result.getIpAddress(), "10.16.177.143");
        assertEquals(result.getIdentd(), "-");
        assertEquals(result.getDateTime(), "[03/Feb/2011:13:39:10 +0000]");
        assertEquals(result.getReferer(), "http://test.com/search.do?keywords=");
        assertEquals(result.getUserAgent(), "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.237 Safari/534.10");

    }

    @org.junit.Test
    public void testParseForCombinedLogExtra() throws Exception {

        //Testing COMBINED log format
        LogEntry result = parser.parse(combinedLogExtraChars);
        assert (result != null);
        assertEquals(result.getIpAddress(), "10.16.177.143");
        assertEquals(result.getIdentd(), "-");
        assertEquals(result.getDateTime(), "[03/Feb/2011:13:39:10 +0000]");
        assertEquals(result.getReferer(), "http://test.com/search.do?keywords=");
        assertEquals(result.getUserAgent(), "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.237 Safari/534.10");

    }


    @org.junit.Test
    public void testParseForCommonLogExtra() throws Exception {

        //Testing COMMON log format
        LogEntry result = parser.parse(commonLogExtra);
        assert (result != null);
        assertEquals(result.getIpAddress(), "10.16.177.143");
        assertEquals(result.getIdentd(), "-");
        assertEquals(result.getDateTime(), "[03/Feb/2011:13:39:10 +0000]");
        assertNull(result.getReferer());
        assertNull(result.getUserAgent());

    }

}
