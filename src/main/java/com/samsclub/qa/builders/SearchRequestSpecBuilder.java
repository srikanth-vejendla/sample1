package com.samsclub.qa.builders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SearchRequestSpecBuilder {

    RequestSpecBuilder requestSpecBuilder;

    String itemNameNumber;
    int clubID;

    public SearchRequestSpecBuilder(String itemNameNumber,int clubID){
        this.itemNameNumber=itemNameNumber;
        this.clubID=clubID;
        requestSpecBuilder = new RequestSpecBuilder().addRequestSpecification(new JSONSpecBuilder().build()).addQueryParam("bypassEGiftCardViewOnly", true).
                addQueryParam("filter", "all").addQueryParam("sortBy",2).addQueryParam("pageSize",30).addQueryParam("pageNum",1)
                .addQueryParam("sortDirection",1).addQueryParam("txt",itemNameNumber).addQueryParam("clubId",clubID);
    }

    public SearchRequestSpecBuilder(String itemNameNumber){
        this.itemNameNumber=itemNameNumber;
        requestSpecBuilder = new RequestSpecBuilder().addRequestSpecification(new JSONSpecBuilder().build()).addQueryParam("bypassEGiftCardViewOnly", true).
                addQueryParam("filter", "all").addQueryParam("upc").addQueryParam("sortBy", 2).addQueryParam("pageSize", 30).addQueryParam("pageNum",1)
                .addQueryParam("cncApp", false).addQueryParam("catId").addQueryParam("sortDirection", 1).addQueryParam("txt",itemNameNumber);
    }


    public RequestSpecification build(){
        return requestSpecBuilder.build();
    }
}
