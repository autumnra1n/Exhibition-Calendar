package com.company.exhibitions.web.utils;

import java.util.ResourceBundle;

public class PageManager {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("pages");

    public PageManager(){}

    public String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
