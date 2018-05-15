package com.company.exhibitions.dao;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

import java.util.List;

public interface ExpositionDao {

    void insertExposition(Exposition exposition) throws DAOException, DataBaseException;
    void updateExposition(Exposition exposition) throws DAOException, DataBaseException;
    void deleteExpositionById(int id) throws DAOException, DataBaseException;
    Exposition findExposition(Exposition exposition) throws DAOException, DataBaseException;
    List<Exposition> findAll() throws DAOException, DataBaseException;
    Exposition findExpositionById(int id) throws DAOException, DataBaseException;
    List<Exposition> findExpositionsByShowroomId(int id) throws DAOException, DataBaseException;
}
