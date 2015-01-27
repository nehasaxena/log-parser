package com.javatechworld.logparser.utils;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: Constants
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public final class Constants {

    public static final String EMPTY_SPACE=" ";
    public static final String COMMA=",";
    public static final String TAB="\t";
    public static final String NEWLINE="\n";

    public static final String COMMON_LOG_PATTERN="^([^ ]+) ([^ ]+) ([^ ]+) (\\[[^\\]]+\\]) \\\"(.*) (.*) (.*)\\\" ([0-9\\-]+) ([0-9\\-]+)$"; 
    public static final String COMBINED_LOG_PATTERN="^([^ ]+) ([^ ]+) ([^ ]+) (\\[[^\\]]+\\]) \\\"(.*) (.*) (.*)\\\" ([0-9\\-]+) ([0-9\\-]+) \\\"([^ ]+)\\\" \\\"(.*)\\\"$";
    public static final String COMBINED_EXTRA_CHAR_AT_END="^([^ ]+) ([^ ]+) ([^ ]+) (\\[[^\\]]+\\]) \\\"(.*) (.*) (.*)\\\" ([0-9\\-]+) ([0-9\\-]+) \\\"([^ ]+)\\\" \\\"(.*)\\\"(.*)$";
    public static final String COMMON_EXTRA_CHAR_AT_END="^([^ ]+) ([^ ]+) ([^ ]+) (\\[[^\\]]+\\]) \\\"(.*) (.*) (.*)\\\" ([0-9\\-]+) ([0-9\\-]+)(.*)$";

     public static final String FILE_SEPARATOR = System.getProperty("file.separator");
}
