package com.company.exhibitions.dao.utils.mysql;

import com.company.exhibitions.dao.utils.DaoEntityListExecutable;

import java.sql.SQLException;
import java.util.List;

public interface MySqlEntityListExecutable<T> extends DaoEntityListExecutable<T> {

    @Override
    List<T> execute() throws SQLException;
}
