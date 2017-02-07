package com.samsclub.qa.builders;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class JSONSpecBuilder extends RequestSpecBuilder {

    public JSONSpecBuilder() {
        this.setContentType(ContentType.JSON);
        this.addHeader("channel","mobiApp");
    }
}
