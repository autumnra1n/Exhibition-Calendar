package com.company.exhibitions.dao.utils;

import java.sql.SQLException;

public interface DaoEntityExecutable<T> {

    T execute() throws SQLException;
}
