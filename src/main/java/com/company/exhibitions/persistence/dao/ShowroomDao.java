package com.company.exhibitions.dao;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;

import java.util.List;

public interface ShowroomDao {

    void insertShowroom(Showroom showroom) throws DAOException, DataBaseException;
    void updateShowroom(Showroom showroom) throws DAOException, DataBaseException;
    void deleteShowroomById(int id) throws DAOException, DataBaseException;
    Showroom findShowroom(Showroom showroom) throws DAOException, DataBaseException;
    List<Showroom> findAll() throws DAOException, DataBaseException;
    Showroom findShowroomById(int id) throws DAOException, DataBaseException;
}
