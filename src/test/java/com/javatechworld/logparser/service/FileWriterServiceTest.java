package com.javatechworld.logparser.service;

import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.utils.Constants;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: FileWriterServiceTest
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class FileWriterServiceTest {

    private FileWriterService writerService = new FileWriterService();

    @org.junit.Test
    public void testWriteToFile() throws Exception {

        StringBuilder fileName = new StringBuilder().append("output").append(Constants.FILE_SEPARATOR).append("test.tsv");

        writerService.openWriter(fileName.toString());
        //Call service
        writerService.writeToFile(getLogEntry());

        writerService.closeWriter();

        //check if the file has been created
        verifyFileExists(fileName, getLogEntry());

    }

    private void verifyFileExists(StringBuilder fileName, LogEntry logEntry){
        FileInputStream fileInputStream = null;
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            fileInputStream = new FileInputStream(fileName.toString());
            in = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(in);

            boolean expectedText = false;
            String line = null;
            while ((line = reader.readLine()) != null) {
                if(line.indexOf("010101") != -1){
                    expectedText = true;
                }
            }
            assert(expectedText);

        } catch (IOException e) {
            fail("Exception thrown while reading file");
        }
    }


    private LogEntry getLogEntry() {

        LogEntry logEntry = new LogEntry();
        logEntry.setClientProtocol("test");
        logEntry.setDateTime("010101");
        logEntry.setHttpMethod("GET");
        logEntry.setHttpStatusCode("200");
        logEntry.setIdentd("-");
        logEntry.setReferer("wwww.google.com");
        logEntry.setRemoteUser("-");
        logEntry.setRequestedResource("/recipes/1223/images/1223_SMALL.jpg");
        logEntry.setResponseSize("6144");
        logEntry.setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.237 Safari/534.10");
  
        return logEntry;

    }

}
