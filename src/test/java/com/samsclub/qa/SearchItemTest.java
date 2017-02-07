package com.samsclub.qa;

import com.samsclub.qa.builders.SearchRequestSpecBuilder;
import com.samsclub.qa.config.BasePath;
import com.samsclub.qa.utils.BaseTest;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class SearchItemTest extends BaseTest{

    RequestSpecification searchRequestSpecification;

    @Test
    public void searchByItemNumber(){
        String itemNo = testData.get(0).toString();
        String itemName = testData.get(1).toString();
        searchRequestSpecification = new SearchRequestSpecBuilder(itemNo).build();
        response = given().spec(searchRequestSpecification).get(BasePath.SEARCH);

        //Validate Response Values and Schema
        response.then().body(matchesJsonSchemaInClasspath("searchSchema.json"));
        assertEquals(itemNo, getPath("txt"));
        assertEquals(itemName, getPath("p[0].n"));
        assertEquals(Long.parseLong(itemNo), Long.parseLong(getPath("p[0].sa[0].in").toString()));
    }

    @Test
    public void searchByName(){
        String itemName = testData.get(0).toString();
        searchRequestSpecification = new SearchRequestSpecBuilder(itemName).build();
        response = given().spec(searchRequestSpecification).get(BasePath.SEARCH);

        //Validate Response Values and Schema
        assertTrue(getPath("p[0].n").toString().contains(itemName));
    }

}
