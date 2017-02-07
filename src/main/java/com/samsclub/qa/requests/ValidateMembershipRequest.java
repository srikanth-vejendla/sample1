package com.samsclub.qa.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ValidateMembershipRequest {
    @JsonProperty("prftSamsData")
    private String prftSamsData;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("memId")
    private String memId;

    public ValidateMembershipRequest(String memId){
        this.memId=memId;
        this.firstName="First";
        this.lastName="Last";
    }

    public ValidateMembershipRequest(String memId,String firstName,String lastName){
        this.memId=memId;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    @JsonProperty("prftSamsData")
    public String getPrftSamsData() {
        return prftSamsData;
    }

    @JsonProperty("prftSamsData")
    public void setPrftSamsData(String prftSamsData) {
        this.prftSamsData = prftSamsData;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }



}
