package com.javatechworld.logparser.exception;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: LogParserException
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class LogParserException extends Exception{


    public LogParserException(String message) {
        super(message);
    }

    public LogParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
