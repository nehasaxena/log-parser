package com.javatechworld.logparser.service.writers;

import java.io.IOException;

import com.google.gson.Gson;
import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;
import com.javatechworld.logparser.utils.Constants;

public class JSONFileWriterService extends FileWriterService {

    private Gson gson = new Gson();


    /**
     * Appends log data to json file
     *
     * @param logEntry {@link LogEntry}
     * @throws LogParserException
     */
    public void writeToFile(LogEntry logEntry) throws LogParserException {
        try{
            bufferedWriter.write(gson.toJson(logEntry) + ",");
        } catch (IOException e) {
            throw new LogParserException(e.getMessage(), e);
        }
    }


    @Override
    public void prependHeader() throws LogParserException {
        try {
            bufferedWriter.write(Constants.JSON_OPENING_CHAR);
        } catch (IOException e) {
            throw new LogParserException(e.getMessage(), e);
        }
    }

    @Override
    public void appendClosingCharacters() throws LogParserException {
        try {
            bufferedWriter.write(Constants.JSON_CLOSING_CHAR);
        } catch (IOException e) {
            throw new LogParserException(e.getMessage(), e);
        }
    }
}
