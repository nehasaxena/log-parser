package com.javatechworld.logparser.service;

import com.javatechworld.logparser.exception.LogParserException;
import com.javatechworld.logparser.utils.Constants;

import java.io.File;
import java.util.GregorianCalendar;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: HttpLogParser
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class HttpLogParser {

    private LogParserService logParserService;
    private FileWriterService fileWriterService;

    private String outputFileName = null;

    public HttpLogParser() {
        this.logParserService = new LogParserService();
        this.fileWriterService = new FileWriterService();
    }

    public static void main(String[] args) throws LogParserException {

        HttpLogParser httpLogParser = new HttpLogParser();

        if (args == null || args.length < 1) {
            throw new LogParserException("\nNo input file name supplied. Please re-run the command with log file location as input file name");
        }
        if (args.length == 2) {
            httpLogParser.outputFileName = args[1];
        }

        httpLogParser.createCSVFromLog(args[0]);


    }

    private void createCSVFromLog(String inputFileName) throws LogParserException {

        logParserService.parseFile(inputFileName, getOutputFileName());
    }


    public String getOutputFileName() {

        if (outputFileName == null) {

            GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
            StringBuilder dateToBeAppended = new StringBuilder().append(calendar.get(GregorianCalendar.YEAR)).append(calendar.get(GregorianCalendar.MONTH) + 1).append(calendar.get(GregorianCalendar.DATE));
            String outputDirectory = "output";
            (new File(outputDirectory)).mkdir();
            StringBuilder outputFile = new StringBuilder(outputDirectory).append(Constants.FILE_SEPARATOR).append("httpLog-").append(dateToBeAppended.toString()).append(".tsv");

            return outputFile.toString();
        } else {
            return outputFileName;
        }
    }

    public void setLogParserService(LogParserService logParserService) {
        this.logParserService = logParserService;
    }

    public void setFileWriterService(FileWriterService fileWriterService) {
        this.fileWriterService = fileWriterService;
    }


}
