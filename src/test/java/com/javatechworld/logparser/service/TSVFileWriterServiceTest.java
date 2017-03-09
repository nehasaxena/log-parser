package com.javatechworld.logparser.service;

import org.junit.Test;
import com.javatechworld.logparser.service.writers.TSVFileWriterService;
import com.javatechworld.logparser.utils.Constants;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: TSVFileWriterServiceTest
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class TSVFileWriterServiceTest extends FileWriterServiceTest{

    private TSVFileWriterService writerService = new TSVFileWriterService();

    private StringBuilder fileName = new StringBuilder().append("output").append(Constants.FILE_SEPARATOR).append("test.tsv");



    @Test
    public void shouldPrependHeadersToTsv() throws Exception {

        writerService.openWriter(fileName.toString());
        //Call service
        writerService.prependHeader();

        writerService.closeWriter();

        //check if the file has been created with correct text
        verifyFileAndData(fileName, "IP Address\tRFC 1413 identity\tRemote User\tDateTime\tHTTP Method\tRequested Resource\tClient Protocol\tHTTP Status Code\tResponse Size\tReferer\tUser Agent\t");

    }


    @Test
    public void shouldWriteLogDateToTSV() throws Exception {

        writerService.openWriter(fileName.toString());
        //Call service
        writerService.writeToFile(getLogEntry());

        writerService.closeWriter();

        //check if the file has been created with correct text
        verifyFileAndData(fileName, "null\t-\t-\t010101\tGET\t/recipes/1223/images/1223_SMALL.jpg\ttest\t200\t6144\twwww.google.com\tMozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534"
            + ".10 (KHTML, like Gecko) Chrome/8.0.552.237 Safari/534.10\t");

    }

}
