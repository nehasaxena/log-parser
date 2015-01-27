package com.javatechworld.logparser.service;


import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.net.URL;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: LogParserServiceTest
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class LogParserServiceTest {

    private LogParserService service = new LogParserService();
    private FileWriterService fileWriterService = mock(FileWriterService.class);
    private LogParserParser parser = mock(LogParserParser.class);


    @org.junit.Before
    public void setup(){
        service.setParser(parser);
        service.setWriterService(fileWriterService);
    }

    @org.junit.Test
    public void testParseNoFileLoc() {

        try {
            service.parseFile(null, "outputFile.txt");
            fail("Was expecting exception to be thrown");
        } catch (LogParserException e) {
            assertTrue(e.getMessage().contains("No file location specified"));
        }

    }


    @org.junit.Test
    public void testParseWrongFileLoc() {

        try {
            service.parseFile("doesntExist.txt", "outputFile.txt");
            fail("Was expecting exception to be thrown");
        } catch (LogParserException e) {
            assertTrue(e.getMessage().contains("Not able to locate file"));
        }

    }

    @org.junit.Test
    public void testParseFile() throws Exception{
        URL filePath = this.getClass().getClassLoader().getResource("tesHttpApache.log");
        when(parser.parse(anyString())).thenReturn(new LogEntry());
        service.parseFile(filePath.getPath(), "outputFile.txt");
        verify(parser,times(4)).parse(anyString());
        verify(fileWriterService, times(4)).writeToFile(any(LogEntry.class));
    }

}
