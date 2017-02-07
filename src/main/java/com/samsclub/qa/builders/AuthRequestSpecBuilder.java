package com.samsclub.qa.builders;


import com.samsclub.qa.config.BasePath;
import com.samsclub.qa.requests.LoginRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthRequestSpecBuilder {

    RequestSpecification requestSpecification;
    String sessionID;

    public AuthRequestSpecBuilder(String userName, String password){
        LoginRequest loginRequest = new LoginRequest(userName,password);
        sessionID = given().spec(new JSONSpecBuilder().build()).body(loginRequest).post(BasePath.ACCOUNT).getCookie("JSESSIONID");
    }

    public RequestSpecification build(){
        return requestSpecification = new RequestSpecBuilder().addCookie("JSESSIONID", sessionID).addRequestSpecification(new JSONSpecBuilder().build()).build();
    }
}
