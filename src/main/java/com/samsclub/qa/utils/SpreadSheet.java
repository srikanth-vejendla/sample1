package com.samsclub.qa.utils;

import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.File("src/main/java/com/samsclub/qa/spreadsheet/spreadsheet.properties")
public interface SpreadSheet {

    @Property("application_name")
    String getApplicationName();

    @Property("service_account_id")
    String getServiceAccountID();

    @Property("sheet_id")
    String getSheetID();

    @Property("spreadsheet_config")
    String getConfigSpreadSheet();

    @Property("spreadsheet_devices")
    String getDevicesSpreadSheet();

    @Property("spreadsheet_mqa_testdata")
    String getMQATestDataSpreadSheet();

    @Property("spreadsheet_prod_testdata")
    String getProdTestDataSpreadSheet();

}
