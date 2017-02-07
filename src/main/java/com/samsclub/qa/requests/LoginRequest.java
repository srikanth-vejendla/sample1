package com.samsclub.qa.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoginRequest {
    @JsonProperty("act")
    private String act;

    @JsonProperty("class")
    private String _class;

    @JsonProperty("prftSamsData")
    private String prftSamsData;

    @JsonProperty("p")
    private String password;

    @JsonProperty("u")
    private String username;

    @JsonProperty("ver")
    private String ver;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginRequest() {
        this.act = "login";
        this._class = "profile";
    }

    /**
     * @param username
     * @param password
     */
    public LoginRequest(String username, String password) {
        super();
        this.act = "login";
        this._class = "profile";
        this.password = password;
        this.username = username;
    }

    @JsonProperty("act")
    public String getAct() {
        return act;
    }

    @JsonProperty("act")
    public void setAct(String act) {
        this.act = act;
    }

    @JsonProperty("class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("class")
    public void setClass_(String _class) {
        this._class = _class;
    }

    @JsonProperty("prftSamsData")
    public String getPrftSamsData() {
        return prftSamsData;
    }

    @JsonProperty("prftSamsData")
    public void setPrftSamsData(String prftSamsData) {
        this.prftSamsData = prftSamsData;
    }

    @JsonProperty("p")
    public String getPassword() {
        return password;
    }

    @JsonProperty("p")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("u")
    public String getUsername() {
        return username;
    }

    @JsonProperty("u")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("ver")
    public String getVersion() {
        return ver;
    }

    @JsonProperty("ver")
    public void setVersion(String ver) {
        this.ver = ver;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
