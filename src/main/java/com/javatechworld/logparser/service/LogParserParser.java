package com.javatechworld.logparser.service;

import com.javatechworld.logparser.domain.LogEntry;
import com.javatechworld.logparser.exception.LogParserException;
import com.javatechworld.logparser.utils.Constants;
import com.javatechworld.logparser.utils.HeaderNames;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: LogParserParser
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class LogParserParser {


    public LogEntry parse(String line) throws LogParserException{

        LogEntry logEntry = null;

        Pattern pattern = Pattern.compile(Constants.COMMON_LOG_PATTERN);
        Matcher matcher = null;
        boolean matched = false;
        boolean commonLogExtra = false;

        if (line != null) {

            logEntry = new LogEntry();

            matcher = pattern.matcher(line.subSequence(0, line.length()));
            matched = matcher.matches();

            if (!matched) {
                pattern = Pattern.compile(Constants.COMBINED_LOG_PATTERN);
                matcher = pattern.matcher(line.subSequence(0, line.length()));
                matched = matcher.matches();
            }
            if (!matched) {
                pattern = Pattern.compile(Constants.COMBINED_EXTRA_CHAR_AT_END);
                matcher = pattern.matcher(line.subSequence(0, line.length()));
                matched = matcher.matches();
            }
            if (!matched) {
                //If none of the pattern above matched, try common log pattern and ignore extra characters after that if any
                pattern = Pattern.compile(Constants.COMMON_EXTRA_CHAR_AT_END);
                matcher = pattern.matcher(line.subSequence(0, line.length()));
                matched = matcher.matches();
                if (matched) {
                    commonLogExtra = true;
                }
            }

            try {
                if (matched) {
                    int tokenSize = matcher.groupCount();
                    if (commonLogExtra) {
                        tokenSize--;
                    }
                    for (HeaderNames header : HeaderNames.values()) {
                        if (header.getPosition() <= tokenSize) {
                            Field field = logEntry.getClass().getDeclaredField(header.name());
                            field.setAccessible(true);
                            field.set(logEntry, matcher.group(header.getPosition()));
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                throw new LogParserException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new LogParserException(e.getMessage(), e);
            }
        }

        return logEntry;
    }
}
