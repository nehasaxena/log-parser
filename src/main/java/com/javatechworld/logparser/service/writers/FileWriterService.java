package com.javatechworld.logparser.service.writers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;

public abstract class FileWriterService {

    FileWriter writer = null;
    BufferedWriter bufferedWriter = null;


    public void openWriter(String outputFileName) throws LogParserException {

        try {
            writer = new FileWriter(outputFileName);
            bufferedWriter = new BufferedWriter(writer);

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

    public abstract void prependHeader() throws LogParserException;

    public abstract void writeToFile(LogEntry logEntry) throws LogParserException;

    public abstract void appendClosingCharacters() throws LogParserException;
}
