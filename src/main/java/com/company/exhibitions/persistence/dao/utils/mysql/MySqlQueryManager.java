package com.company.exhibitions.dao.utils.mysql;

import com.company.exhibitions.dao.utils.QueryManager;

import java.util.ResourceBundle;

public class MySqlQueryManager implements QueryManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("mySqlQueries");

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

