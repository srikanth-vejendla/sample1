package com.samsclub.qa.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RegisterMembershipRequest {
    @JsonProperty("userClientDeviceType")
    private Integer userClientDeviceType;

    @JsonProperty("confPasswd")
    private String confPasswd;

    @JsonProperty("prftSamsData")
    private String prftSamsData;

    @JsonProperty("emailId")
    private String emailId;

    @JsonProperty("emailPref")
    private String emailPref;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("memId")
    private String memId;

    @JsonProperty("passwd")
    private String passwd;

    public RegisterMembershipRequest(String membershipNumber,String email,String password){
        this.memId = membershipNumber;
        this.emailId=email;
        this.passwd=password;
        this.confPasswd=password;
        this.emailPref="y";
        this.firstName="First";
        this.lastName="Last";
    }

    @JsonProperty("userClientDeviceType")
    public Integer getUserClientDeviceType() {
        return userClientDeviceType;
    }

    @JsonProperty("userClientDeviceType")
    public void setUserClientDeviceType(Integer userClientDeviceType) {
        this.userClientDeviceType = userClientDeviceType;
    }

    @JsonProperty("confPasswd")
    public String getConfPasswd() {
        return confPasswd;
    }

    @JsonProperty("confPasswd")
    public void setConfPasswd(String confPasswd) {
        this.confPasswd = confPasswd;
    }

    @JsonProperty("prftSamsData")
    public String getPrftSamsData() {
        return prftSamsData;
    }

    @JsonProperty("prftSamsData")
    public void setPrftSamsData(String prftSamsData) {
        this.prftSamsData = prftSamsData;
    }

    @JsonProperty("emailId")
    public String getEmailId() {
        return emailId;
    }

    @JsonProperty("emailId")
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @JsonProperty("emailPref")
    public String getEmailPref() {
        return emailPref;
    }

    @JsonProperty("emailPref")
    public void setEmailPref(String emailPref) {
        this.emailPref = emailPref;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("memId")
    public String getMemId() {
        return memId;
    }

    @JsonProperty("memId")
    public void setMemId(String memId) {
        this.memId = memId;
    }

    @JsonProperty("passwd")
    public String getPasswd() {
        return passwd;
    }

    @JsonProperty("passwd")
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
