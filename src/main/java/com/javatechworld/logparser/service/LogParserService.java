package com.javatechworld.logparser.service;

import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    private FileWriterService writerService = new FileWriterService();

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

            writerService.openWriter(outputFileName);

            String line = null;
            while ((line = reader.readLine()) != null) {
                writerService.writeToFile(parser.parse(line));
            }


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
                writerService.closeWriter();
            }
        }

        return logEntries;
    }

    public void setParser(LogParserParser parser) {
        this.parser = parser;
    }

    public void setWriterService(FileWriterService writerService) {
        this.writerService = writerService;
    }
}
