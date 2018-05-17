package com.company.exhibitions.dao.utils;

import java.util.List;

public interface DaoEntityListExecutable<T> {

    List<T> execute() throws Exception;
}
