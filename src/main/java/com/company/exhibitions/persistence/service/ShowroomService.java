package com.company.exhibitions.service;

import com.company.exhibitions.dao.ShowroomDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;

public class ShowroomService {

    private final MySqlDaoFactory factory = MySqlDaoFactory.getInstance();

    public void insertShowroom(Showroom showroom) throws ServiceException {
        try {
            ShowroomDao showroomDao = factory.getShowroomDao();
            try {
                showroomDao.insertShowroom(showroom);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void updateShowroom(Showroom showroom) throws ServiceException {
        try {
            ShowroomDao showroomDao = factory.getShowroomDao();
            try {
                showroomDao.updateShowroom(showroom);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteShowroom(int id) throws ServiceException{
        try {
            ShowroomDao showroomDao = factory.getShowroomDao();
            try {
                showroomDao.deleteShowroomById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public List<Showroom> findAll() throws ServiceException{
        try {
            ShowroomDao showroomDao = factory.getShowroomDao();
            try {
                return showroomDao.findAll();
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public Showroom findShowroomById (int id) throws ServiceException{
        try {
            ShowroomDao showroomDao = factory.getShowroomDao();
            try {
                return showroomDao.findShowroomById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}
