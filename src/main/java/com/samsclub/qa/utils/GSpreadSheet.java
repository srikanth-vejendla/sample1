package com.samsclub.qa.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import ru.qatools.properties.PropertyLoader;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GSpreadSheet {

    private static SpreadSheet spreadSheet = PropertyLoader.newInstance().populate(SpreadSheet.class);

    private static String env = StringHelper.getValueOrDefault(System.getProperty("env"), "mqa");

    /** Application name. */
    private static final String APPLICATION_NAME = spreadSheet.getApplicationName();

    /** Sheet ID */
    private static final String SHEET_ID = spreadSheet.getSheetID();

    /** Google Sheets Instance */
    private static Sheets service=getSheetsService();

    /** MQA Sheet Cell Values */
    private static List<List<Object>> mqaSheet = getDataSheet(spreadSheet.getMQATestDataSpreadSheet());

    /** Prod Sheet Cell Values */
    private static List<List<Object>> prodSheet = getDataSheet(spreadSheet.getProdTestDataSpreadSheet());

    /**
     * Build and return an authorized Sheets API client service.
     * @return an authorized Sheets API client service
     * @throws Exception
     */
    private static Sheets getSheetsService(){
        HttpTransport HTTP_TRANSPORT = null;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream resourceAsStream = GSpreadSheet.class.getClassLoader().getResourceAsStream("client_secret.json");
        GoogleCredential credential = null;
        try {
            credential = GoogleCredential.fromStream(resourceAsStream).createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Sheets.Builder(HTTP_TRANSPORT, JacksonFactory.getDefaultInstance(), credential).setApplicationName(APPLICATION_NAME).build();
    }

    public static List<List<Object>> getDataSheet(String environment){
        try {
            return service.spreadsheets().values()
                    .get(SHEET_ID, environment)
                    .execute().getValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Object> getTestCaseData(String TestCaseName) throws Exception{
        int testCaseRowNo=0;
        List<Object> testData;
        List<List<Object>> dataSheet;
        if(env.equalsIgnoreCase("PROD")){
            dataSheet = prodSheet;
        }else{
            dataSheet=mqaSheet;
        }
        for (int i=0;i<dataSheet.size();i++){
            if(TestCaseName.equals(dataSheet.get(i).get(1).toString())){
                testCaseRowNo=i;
                break;
            }
        }
        try{
            testData=dataSheet.get(testCaseRowNo).subList(5,8);
        }catch (IndexOutOfBoundsException e){
            testData=null;
        }

        return testData;
    }

    public static List<Object> getTestCaseCredentials(String TestCaseName) throws Exception{
        int testCaseRowNo=0;
        List<Object> testCredentials=null;
        List<List<Object>> dataSheet;
        if(env.equalsIgnoreCase("PROD")){
            dataSheet = prodSheet;
        }else{
            dataSheet = mqaSheet;
        }

        for (int i=0;i<dataSheet.size();i++){
            if(TestCaseName.equals(dataSheet.get(i).get(1).toString())){
                testCaseRowNo=i;
                break;
            }
        }
        try{
            testCredentials=dataSheet.get(testCaseRowNo).subList(3,5);
        }catch (IndexOutOfBoundsException e){
            testCredentials=null;
        }
        return testCredentials;
    }

}
