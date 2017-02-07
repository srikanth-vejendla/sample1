package com.samsclub.qa.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

    private int count = 1;
    private int retryCount = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count < retryCount) {
            System.out.println("Retrying test " + iTestResult.getName() + " with status "
                    + getResultStatusName(iTestResult.getStatus()) + " for the " + (retryCount) + " time(s).");
            count++;
            return true;
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if(status==1)
            resultName = "SUCCESS";
        if(status==2)
            resultName = "FAILURE";
        if(status==3)
            resultName = "SKIP";
        return resultName;
    }
}
