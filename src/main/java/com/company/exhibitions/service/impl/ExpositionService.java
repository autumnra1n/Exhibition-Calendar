package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.ExpositionDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.Mapper;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpositionService implements IExpositionService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Exposition> executor = new ServiceExecutor<>();

    @Override
    public void insertExposition(Map <String, String> parameters) throws ServiceException {
        ExpositionDao expositionDao = factory.getExpositionDao();
        executor.perform(() -> expositionDao.insertExposition(buildExposition(parameters)));
    }

    @Override
    public void updateExposition(Map <String, String> parameters) throws ServiceException {
        ExpositionDao expositionDao = factory.getExpositionDao();
        executor.perform(() -> expositionDao.updateExposition(buildExpositionWithId(parameters)));
    }

    @Override
    public Exposition findExposition(Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntitySelect(() -> expositionDao.findExposition(buildExposition(parameters)));
    }

    @Override
    public void deleteExposition(Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        executor.perform(() -> expositionDao.deleteExpositionById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public List<Exposition> findAll() throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntityListSelect(expositionDao::findAll);
    }

    @Override
    public Exposition findExpositionById (Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntitySelect(() -> expositionDao.findExpositionById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public List<Exposition> findExpositionByShowroomId (Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntityListSelect(() -> expositionDao.findExpositionsByShowroomId(Integer.valueOf(parameters.get("id"))));
    }

    private Exposition buildExposition(Map <String, String> parameters){
        return new Exposition.ExpositionBuilder(parameters.get("theme"))
                .setDateStart(Date.valueOf(parameters.get("date")))
                .setStartTime(Time.valueOf(parameters.get("time")))
                .setDescription(parameters.get("description"))
                .setShowroom(new Showroom.ShowroomBuilder(parameters.get("showroomName"))
                        .setId(Integer.valueOf(parameters.get("showroomId")))
                        .build())
                .build();
    }

    private Exposition buildExpositionWithId(Map <String, String> parameters){
        return new Exposition.ExpositionBuilder(parameters.get("theme"))
                .setId(Integer.valueOf(parameters.get("id")))
                .setDateStart(Date.valueOf(parameters.get("date")))
                .setStartTime(Time.valueOf(parameters.get("time")))
                .setDescription(parameters.get("description"))
                .setShowroom(new Showroom.ShowroomBuilder(parameters.get("showroomName"))
                        .setId(Integer.valueOf(parameters.get("showroomId")))
                        .build())
                .build();
    }
}
