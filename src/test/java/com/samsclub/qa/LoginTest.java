package com.samsclub.qa;

import com.samsclub.qa.builders.AuthResponseSpecBuilder;
import com.samsclub.qa.config.BasePath;
import com.samsclub.qa.requests.LoginRequest;
import com.samsclub.qa.utils.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class LoginTest extends BaseTest{

    LoginRequest loginRequest;
    AuthResponseSpecBuilder authResponseSpecBuilder;

    @Test
    public void loginWithValidCredentials() throws Exception{
        String membershipType = testData.get(0).toString();
        String membershipNumber = testData.get(1).toString();
        loginRequest=new LoginRequest(testUserName,testPassword);
        authResponseSpecBuilder=new AuthResponseSpecBuilder();

        response=given().spec(jsonSpecBuilder).body(loginRequest).post(BasePath.ACCOUNT);

        //Validate Response Values and Schema
        response.then().spec(authResponseSpecBuilder.build());
        assertEquals(loginRequest.getUsername(), getPath("em"));
        assertEquals(membershipType,getPath("mbr.mt"));
        assertEquals(membershipNumber,getPath("mbr.mn"));
    }

    @Test
    public void loginWithInvalidEmail(){
        String errMessage = testData.get(0).toString();
        String errCode = testData.get(1).toString();
        loginRequest=new LoginRequest(testUserName,testPassword);
        response=given().spec(jsonSpecBuilder).body(loginRequest).post(BasePath.ACCOUNT);

        //Validate Response Values
        assertEquals((Integer)2,getPath("s"));
        assertEquals(errMessage,getPath("err"));
        assertEquals(errCode,getPath("errCode"));
    }

    @Test
    public void loginWithInvalidPassword(){
        String errMessage = testData.get(0).toString();
        String errCode = testData.get(1).toString();
        String email = "apps@mqa"+generateRandomNumber(1,25)+".com";
        loginRequest=new LoginRequest(email,"wrongpassword");
        response=given().spec(jsonSpecBuilder).body(loginRequest).post(BasePath.ACCOUNT);

        //Validate Response Values
        assertEquals((Integer)1, getPath("s"));
        assertEquals(errMessage,getPath("err"));
        assertEquals(errCode,getPath("errCode"));
    }

}
