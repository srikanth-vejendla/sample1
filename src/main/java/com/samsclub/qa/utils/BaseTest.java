package com.samsclub.qa.utils;

import com.samsclub.qa.builders.JSONSpecBuilder;
import com.samsclub.qa.config.BasePath;
import com.samsclub.qa.config.BaseURI;
import com.samsclub.qa.requests.RegisterMembershipRequest;
import com.samsclub.qa.requests.ValidateMembershipRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected Response response;
    protected RequestSpecification jsonSpecBuilder=new JSONSpecBuilder().build();
    private static String url = null;
    protected List<Object> testData=null;
    protected String testUserName;
    protected String testPassword;

    static {
        String environment=StringHelper.getValueOrDefault(System.getProperty("env"), "mqa");
        //Default Environment- Major QA
        if (environment.equalsIgnoreCase("mqa")){
            RestAssured.useRelaxedHTTPSValidation();
            url = BaseURI.MAJOR_QA;
        }else if(environment.equalsIgnoreCase("staging")){
            RestAssured.useRelaxedHTTPSValidation();
            url = BaseURI.STAGING;
        }else if(environment.equalsIgnoreCase("production")){
            url = BaseURI.PRODUCTION;
        }

        RestAssured.baseURI=url;
        //Print all Request and Response details on Test Failure
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    /**
     * Initialize Test spreadsheet for Test Method
     * @throws Exception
     */
    @BeforeMethod
    public void init(Method method) throws Exception{
        String methodName = method.getName();
        testData = GSpreadSheet.getTestCaseData(methodName);
        testUserName = GSpreadSheet.getTestCaseCredentials(methodName).get(0).toString();
        testPassword = GSpreadSheet.getTestCaseCredentials(methodName).get(1).toString();
    }

    /**
     * Create Member using Membership Tool for Major QA Environment
     * @param membershipType
     * @param state
     * @param email
     * @return Membership # and Email
     */
    protected HashMap<String, String> createNewMembership(String membershipType, String state, String email){
        response = given().pathParam("membershipType", membershipType).pathParam("state",state).pathParam("email",email)
                .get("http://10.227.66.246:8080/energia/tools/membership?type={membershipType}&state={state}&email={email}");
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("mn",response.getBody().asString().split("Membership #: ")[1].split("<BR>")[0]);
        map.put("em",response.getBody().asString().split("Email: ")[1].split("<BR>")[0]);
        return map;
    }

    /**
     * Create Member using Membership Tool for Major QA Environment
     * @param email
     * @return Membership # and Email
     */

    protected HashMap<String, String> createNewMembership(String email){
        response = given().pathParam("membershipType", "SAVINGS").pathParam("state","AR").pathParam("email",email)
                .get("http://10.227.66.246:8080/energia/tools/membership?type={membershipType}&state={state}&email={email}");
        response.getBody().prettyPrint();
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("mn",response.getBody().asString().split("Membership #: ")[1].split("<BR>")[0]);
        map.put("em",response.getBody().asString().split("Email: ")[1].split("<BR>")[0]);
        return map;
    }

    /**
     * Create Member using Membership Tool for Major QA Environment
     * @param membershipType
     * @param state
     * @return Membership # and Email
     */
    protected HashMap<String, String> createNewMembership(String membershipType, String state){
        response = given().pathParam("membershipType", membershipType).pathParam("state",state)
                .get("http://10.227.66.246:8080/energia/tools/membership?type={membershipType}&state={state}");
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("mn",response.getBody().asString().split("Membership #: ")[1].split("<BR>")[0]);
        map.put("em",response.getBody().asString().split("Email: ")[1].split("<BR>")[0]);
        return map;
    }

    /**
     * Create Member using Membership Tool for Major QA Environment
     * @return Membership # and Email
     */
    protected HashMap<String, String> createNewMembership(){
        response = given().get("http://10.227.66.246:8080/energia/tools/membership?type=SAVINGS&state=AR");
        HashMap<String,String> data = new HashMap<String, String>();
        data.put("mn",response.getBody().asString().split("Membership #: ")[1].split("<BR>")[0]);
        data.put("em",response.getBody().asString().split("Email: ")[1].split("<BR>")[0]);
        return data;
    }

    /**
     * Register Account on Major QA
     * @param membershipNumber
     * @param email
     * @param password
     * @return Membership #,Email,First Name,Last Name
     */
    protected HashMap<String, String> registerMembership(String membershipNumber, String email, String password){
        ValidateMembershipRequest validateMembershipRequest = new ValidateMembershipRequest(membershipNumber);
        RegisterMembershipRequest registerMembershipRequest = new RegisterMembershipRequest(membershipNumber,email,password);

        //Validate Member
        Response validateResp = given().contentType(ContentType.JSON).body(validateMembershipRequest).post(BasePath.VALIDATE_MEMBERSHIP);

        //Register Member
        Response registerResp = given().contentType(ContentType.JSON).cookie("JSESSIONID", validateResp.getCookie("JSESSIONID")).body(registerMembershipRequest).post(BasePath.REGISTER_MEMBERSHIP);
        HashMap<String,String> data = new HashMap<String, String>();
        data.put("mn",registerResp.path("memId").toString());
        data.put("em",registerResp.path("email").toString());
        data.put("fn",registerResp.path("firstName").toString());
        data.put("ln",registerResp.path("lastName").toString());
        return data;
    }

    /**
     * Register Account on Major QA Environment
     * @return Membership #,Email,Password,First Name,Last Name
     */
    protected HashMap<String, String> registerMembership(){
        HashMap<String,String> member = createNewMembership();
        String membershipNumber = member.get("mn");
        String email = member.get("em");
        String password = "test1test";
        ValidateMembershipRequest validateMembershipRequest = new ValidateMembershipRequest(membershipNumber);
        RegisterMembershipRequest registerMembershipRequest = new RegisterMembershipRequest(membershipNumber,email,password);

        //Validate Member
        Response validateResp = given().contentType(ContentType.JSON).body(validateMembershipRequest).post(BasePath.VALIDATE_MEMBERSHIP);

        //Register Member
        Response registerResp = given().contentType(ContentType.JSON).cookie("JSESSIONID", validateResp.getCookie("JSESSIONID")).body(registerMembershipRequest).post(BasePath.REGISTER_MEMBERSHIP);
        HashMap<String,String> data = new HashMap<String, String>();
        data.put("mn",registerResp.path("memId").toString());
        data.put("em",registerResp.path("email").toString());
        data.put("pwd",password);
        data.put("fn",registerResp.path("firstName").toString());
        data.put("ln",registerResp.path("lastName").toString());
        return data;
    }

    /**
     * Register Account on Major QA Environment
     * @param emailID
     * @return Membership #,Email,Password,First Name,Last Name
     */
    protected HashMap<String, String> registerMembership(String emailID){
        HashMap<String,String> member = createNewMembership(emailID);
        String membershipNumber = member.get("mn");
        String email = member.get("em");
        String password = "test1test";
        ValidateMembershipRequest validateMembershipRequest = new ValidateMembershipRequest(membershipNumber);
        RegisterMembershipRequest registerMembershipRequest = new RegisterMembershipRequest(membershipNumber,email,password);

        //Validate Member
        Response validateResp = given().contentType(ContentType.JSON).body(validateMembershipRequest).post(BasePath.VALIDATE_MEMBERSHIP);

        //Register Member
        Response registerResp = given().contentType(ContentType.JSON).cookie("JSESSIONID", validateResp.getCookie("JSESSIONID")).body(registerMembershipRequest).post(BasePath.REGISTER_MEMBERSHIP);
        HashMap<String,String> data = new HashMap<String, String>();
        data.put("mn",registerResp.path("memId").toString());
        data.put("em",registerResp.path("email").toString());
        data.put("pwd",password);
        data.put("fn",registerResp.path("firstName").toString());
        data.put("ln",registerResp.path("lastName").toString());
        return data;
    }

    /**
     * Get JSession ID from Response Object
     * @param response
     * @return JSession ID
     */
    protected String getJSESSIONID(Response response){
        return response.getCookie("JSESSIONID");
    }

    /**
     * Get Value from Response based on the Key
     * @param path
     * @return Key Value
     */
    protected <T> T getPath(String path){
        return response.getBody().path(path);
    }

    /**
     * Get Value from Response based on the Key
     * @param response
     * @param path
     * @return Key Value
     */
    protected String getPath(Response response, String path){
        return response.getBody().path(path);
    }

    /**
     * Generate Random Number in the given Range
     * @param lowerBound Lower Boundary
     * @param upperBound Upper Boundary
     * @return Random Integer
     */
    public int generateRandomNumber(int lowerBound,int upperBound){
        return (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
    }

}
