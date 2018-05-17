package com.company.exhibitions.dao.utils.mysql;

import com.company.exhibitions.dao.utils.DaoExecutable;

import java.sql.SQLException;

public interface MySqlExecutable extends DaoExecutable {

    @Override
    void execute() throws SQLException;
}
