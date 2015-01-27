package com.javatechworld.logparser.utils;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: HeaderNames
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public enum HeaderNames {


    ipAddress(1,"IP Address"),
    identd(2, "RFC 1413 identity"),
    remoteUser(3,"Remote User"),
    dateTime(4,"DateTime"),
    httpMethod(5,"HTTP Method"),
    requestedResource(6,"Requested Resource"),
    clientProtocol(7,"Client Protocol"),
    httpStatusCode(8,"HTTP Status Code"),
    responseSize(9,"Response Size"),
    referer(10,"Referer"),
    userAgent(11,"User Agent");


    private int position;
    private String header;

    HeaderNames(int position, String header) {
        this.position = position;
        this.header = header;
    }

    public int getPosition() {
        return position;
    }

    public String getHeader() {
        return header;
    }
}
