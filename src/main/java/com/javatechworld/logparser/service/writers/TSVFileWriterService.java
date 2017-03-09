package com.javatechworld.logparser.service.writers;

import java.io.IOException;
import java.lang.reflect.Field;

import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;
import com.javatechworld.logparser.utils.Constants;
import com.javatechworld.logparser.utils.HeaderNames;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: TSVFileWriterService
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class TSVFileWriterService extends FileWriterService {

    @Override
    public void prependHeader() throws LogParserException {

        try {
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


    /**
     * Appends log data to tsv file
     *
     * @param logEntry {@link com.javatechworld.logparser.domain.LogEntry}
     * @throws LogParserException
     */
    @Override
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

        } catch (NoSuchFieldException | IOException | IllegalAccessException e) {
            throw new LogParserException(e.getMessage(), e);
        }
    }

    /**
     * TSV file doesn't require any ending character
     */
    @Override
    public void appendClosingCharacters() {
        //Do Nothing
    }
}
