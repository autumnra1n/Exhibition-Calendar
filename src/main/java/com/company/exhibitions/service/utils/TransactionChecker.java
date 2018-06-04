package com.company.exhibitions.service.utils;

import com.company.exhibitions.transaction.Transactional;

import java.lang.reflect.Method;
import java.util.List;

public class TransactionChecker<T> {

    public boolean isTransactional(ServiceExecutable ex){
        return ex.getClass().getEnclosingMethod().isAnnotationPresent(Transactional.class);
    }

    public boolean isEntitySelectTransactional(ServiceEntityExecutable<T> ex){
        return ex.getClass().getEnclosingMethod().isAnnotationPresent(Transactional.class);
    }

    public boolean isEntityListSelectTransactional(ServiceEntityListExecutable<T> ex){
        return ex.getClass().getEnclosingMethod().isAnnotationPresent(Transactional.class);
    }
}
