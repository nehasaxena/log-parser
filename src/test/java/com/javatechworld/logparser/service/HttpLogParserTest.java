package com.javatechworld.logparser.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.javatechworld.logparser.Helper;
import com.javatechworld.logparser.exception.LogParserException;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: HttpLogParserTest
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class HttpLogParserTest {

    private HttpLogParser httpLogParser = new HttpLogParser();
    private LogParserService logParserService = mock(LogParserService.class);

    @org.junit.Before
    public void setup() {
        httpLogParser.setLogParserService(logParserService);
    }

    @org.junit.Test
    public void testmain() {

        try {
            HttpLogParser.main(null);
            fail("Was expecting exception to be thrown");
        } catch (LogParserException e) {
            assertTrue(e.getMessage().contains("No input file name supplied"));
        }


        try {
            HttpLogParser.main(new String[]{"test"});
            fail("Was expecting exception to be thrown");
        } catch (LogParserException e) {
            assertTrue(e.getMessage().contains("Not able to locate file"));
        }
    }

    @org.junit.Test
    public void testCreateCSVFromLog() throws Exception {

        Helper.invokeMethod(httpLogParser, "createOutputFileFromLog", new Class[]{String.class}, new Object[]{"test"});
        verify(logParserService).parseFile(anyString(), anyString());
    }


    @org.junit.Test
    public void testGetOutputFileName() throws Exception {
        String result = (String) Helper.invokeMethod(httpLogParser, "getOutputFileName", null, null);

        assertTrue(result.contains("output"));

    }

}
