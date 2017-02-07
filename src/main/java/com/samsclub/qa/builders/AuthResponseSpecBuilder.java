package com.samsclub.qa.builders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.core.Is.is;

import io.restassured.module.jsv.JsonSchemaValidator;

public class AuthResponseSpecBuilder {

    ResponseSpecBuilder responseSpecBuilder;

    public AuthResponseSpecBuilder(){
        responseSpecBuilder=new ResponseSpecBuilder();

        //Assertions
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectBody("s", is(0));
        responseSpecBuilder.expectBody("err", is(""));
        responseSpecBuilder.expectBody("errCode", is(""));

        //Schema Validation
        responseSpecBuilder.expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("loginSchema.json"));
    }

    public ResponseSpecification build(){
        return responseSpecBuilder.build();
    }
}
