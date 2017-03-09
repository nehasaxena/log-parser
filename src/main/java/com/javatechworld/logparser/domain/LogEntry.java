package com.javatechworld.logparser.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Title:       Log Parser
 * Copyright:   Copyright (c) 2011
 * Company:     Java tech world
 * Description: HttpLog
 *
 * @author: $Author: NS $
 * @version: $Revision: $
 * @date: $Date: $
 */

public class LogEntry {
    @SerializedName("IP Address")
    private String ipAddress;
    @SerializedName("RFC 1413 identity")
    private String identd;
    @SerializedName("Remote User")
    private String remoteUser;
    @SerializedName("DateTime")
    private String dateTime;
    @SerializedName("HTTP Method")
    private String httpMethod;
    @SerializedName("Requested Resource")
    private String requestedResource;
    @SerializedName("Client Protocol")
    private String clientProtocol;
    @SerializedName("HTTP Status Code")
    private String httpStatusCode;
    @SerializedName("Response Size")
    private String responseSize;
    @SerializedName("Referer")
    private String referer;
    @SerializedName("User Agent")
    private String userAgent;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIdentd() {
        return identd;
    }

    public void setIdentd(String identd) {
        this.identd = identd;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestedResource() {
        return requestedResource;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public void setRequestedResource(String requestedResource) {
        this.requestedResource = requestedResource;
    }

    public String getClientProtocol() {
        return clientProtocol;
    }

    public void setClientProtocol(String clientProtocol) {
        this.clientProtocol = clientProtocol;
    }

    public String getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(String responseSize) {
        this.responseSize = responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
