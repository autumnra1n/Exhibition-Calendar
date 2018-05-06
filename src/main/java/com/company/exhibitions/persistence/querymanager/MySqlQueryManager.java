package com.company.exhibitions.querymanager;

import java.util.ResourceBundle;

public class MySqlQueryManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("mySqlQueries");

    private MySqlQueryManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

