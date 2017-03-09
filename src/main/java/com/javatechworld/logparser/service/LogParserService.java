package com.javatechworld.logparser.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;
import com.javatechworld.logparser.service.writers.JSONFileWriterService;
import com.javatechworld.logparser.service.writers.TSVFileWriterService;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: LogParserService
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class LogParserService {

    private LogParserParser parser;
    private List<LogEntry> logEntries = new ArrayList<LogEntry>();
    private TSVFileWriterService tsvService = new TSVFileWriterService();
    private JSONFileWriterService jsonService = new JSONFileWriterService();

    public LogParserService() {
        this.parser = new LogParserParser();
    }

    public List<LogEntry> parseFile(String fileLocation, String outputFileName) throws LogParserException {

        if (fileLocation == null) {
            throw new LogParserException("No file location specified");
        }

        FileInputStream fileInputStream = null;
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            fileInputStream = new FileInputStream(fileLocation);
            in = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(in);

            tsvService.openWriter(outputFileName+".tsv");
            tsvService.prependHeader();
            jsonService.openWriter(outputFileName+".json");
            jsonService.prependHeader();

            String line = null;
            LogEntry logEntry = null;
            while ((line = reader.readLine()) != null) {
                logEntry = parser.parse(line);
                tsvService.writeToFile(logEntry);
                jsonService.writeToFile(logEntry);
            }

            jsonService.appendClosingCharacters();

        } catch (FileNotFoundException e) {
            throw new LogParserException("Not able to locate file. Please check the file location entered:" + fileLocation+ "\n"+e.getMessage(), e);
        } catch (IOException io) {
            throw new LogParserException("Error while reading file:" + fileLocation+ " Make sure that file is accessible.\n"+io.getMessage(), io);
        }
        finally {
            //Close all the streams
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (in != null) {
                    in.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                //Do Nothing
            }finally {
                tsvService.closeWriter();
            }
        }

        return logEntries;
    }

    public void setParser(LogParserParser parser) {
        this.parser = parser;
    }

    public void setTsvFileWriterService(TSVFileWriterService tsvFileWriterService) {
        this.tsvService = tsvFileWriterService;
    }
}
