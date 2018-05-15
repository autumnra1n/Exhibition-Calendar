package com.company.exhibitions.dao.utils.mysql;

import com.company.exhibitions.dao.utils.DaoEntityExecutable;

import java.sql.SQLException;

public interface MySqlEntityExecutable<T> extends DaoEntityExecutable<T> {

    @Override
    T execute() throws SQLException;
}
