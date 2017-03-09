package com.javatechworld.logparser.service;

import org.junit.Test;
import com.javatechworld.logparser.service.writers.JSONFileWriterService;
import com.javatechworld.logparser.utils.Constants;

public class JsonFileWriterServiceTest extends FileWriterServiceTest{

    private JSONFileWriterService jsonService = new JSONFileWriterService();
    private StringBuilder fileName = new StringBuilder().append("output").append(Constants.FILE_SEPARATOR).append("test.json");


    @Test
    public void shouldPrependHeadersToTsv() throws Exception {

        jsonService.openWriter(fileName.toString());
        //Call service
        jsonService.prependHeader();

        jsonService.closeWriter();

        //check if the file has been created with correct text
        verifyFileAndData(fileName, "[");

    }


    @Test
    public void shouldWriteJsonToFile() throws Exception {


        jsonService.openWriter(fileName.toString());
        //Call service
        jsonService.writeToFile(getLogEntry());

        jsonService.closeWriter();

        //check if the file has been created with correct text
        verifyFileAndData(fileName, "{\"RFC 1413 identity\":\"-\",\"Remote User\":\"-\",\"DateTime\":\"010101\",\"HTTP Method\":\"GET\",\"Requested Resource\":\"/recipes/1223/images/1223_SMALL"
            + ".jpg\",\"Client Protocol\":\"test\",\"HTTP Status Code\":\"200\",\"Response Size\":\"6144\",\"Referer\":\"wwww.google.com\",\"User Agent\":\"Mozilla/5.0 (Windows; U; Windows NT 5.1; "
            + "en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.237 Safari/534.10\"},");

    }

    @Test
    public void shouldAppendClosingCharacters() throws Exception {

        jsonService.openWriter(fileName.toString());
        //Call service
        jsonService.appendClosingCharacters();

        jsonService.closeWriter();

        //check if the file has been created with correct text
        verifyFileAndData(fileName, "]");

    }

}