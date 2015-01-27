package com.javatechworld.logparser.service;

import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;
import com.javatechworld.logparser.utils.Constants;
import com.javatechworld.logparser.utils.HeaderNames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: FileWriterService
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class FileWriterService {

    FileWriter writer = null;
    BufferedWriter bufferedWriter = null;


    public void openWriter(String outputFileName) throws LogParserException {

        try {
            writer = new FileWriter(outputFileName);
            bufferedWriter = new BufferedWriter(writer);

            StringBuilder output = new StringBuilder();
            for (HeaderNames header : HeaderNames.values()) {
                output.append(header.getHeader()).append(Constants.TAB);
            }
            output.append(Constants.NEWLINE);

            bufferedWriter.write(output.toString());
        } catch (IOException e) {
            throw new LogParserException(e.getMessage(), e);
        }
    }


    public void closeWriter() {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Appends log data to tsv file
     *
     * @param logEntry {@link com.javatechworld.logparser.domain.LogEntry}
     * @throws LogParserException
     */
    public void writeToFile(LogEntry logEntry) throws LogParserException {

        StringBuilder output = new StringBuilder();

        try {
            for (HeaderNames header : HeaderNames.values()) {
                Field field = logEntry.getClass().getDeclaredField(header.name());
                field.setAccessible(true);
                output.append(field.get(logEntry)).append(Constants.TAB);
            }
            output.append(Constants.NEWLINE);

            bufferedWriter.write(output.toString());

        } catch (NoSuchFieldException e) {
            throw new LogParserException(e.getMessage(), e);
        } catch (IOException e) {
            throw new LogParserException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new LogParserException(e.getMessage(), e);
        }
    }
}
