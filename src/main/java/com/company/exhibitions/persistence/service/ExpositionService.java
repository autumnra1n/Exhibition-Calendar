package com.company.exhibitions.service;

import com.company.exhibitions.dao.ExpositionDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;

public class ExpositionService {

    private final MySqlDaoFactory factory = MySqlDaoFactory.getInstance();

    public void insertExposition(Exposition exposition) throws ServiceException {
        try {
            ExpositionDao expositionDao = factory.getExpositionDao();
            try {
                expositionDao.insertExposition(exposition);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void updateExposition(Exposition exposition) throws ServiceException {
        try {
            ExpositionDao expositionDao = factory.getExpositionDao();
            try {
                expositionDao.updateExposition(exposition);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteExposition(int id) throws ServiceException{
        try {
            ExpositionDao expositionDao = factory.getExpositionDao();
            try {
                expositionDao.deleteExpositionById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public List<Exposition> findAll() throws ServiceException{
        try {
            ExpositionDao expositionDao = factory.getExpositionDao();
            try {
                return expositionDao.findAll();
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public Exposition findExpositionById (int id) throws ServiceException{
        try {
            ExpositionDao expositionDao = factory.getExpositionDao();
            try {
                return expositionDao.findExpositionById(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public List<Exposition> findExpositionByShowroomId (int id) throws ServiceException{
        try {
            ExpositionDao expositionDao = factory.getExpositionDao();
            try {
                return expositionDao.findExpositionsByShowroomId(id);
            } catch (DAOException e) {

            }

        } catch (DataBaseException e) {
            throw new ServiceException(e);
        }
        return null;
    }
}
