package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.ExpositionDao;
import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IExpositionService;
import com.company.exhibitions.service.utils.ServiceEntityExecutable;
import com.company.exhibitions.service.utils.ServiceEntityListExecutable;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.DateUtil;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.utils.TimeUtil;

import java.util.List;
import java.util.Map;

public class ExpositionService implements IExpositionService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Exposition> executor = new ServiceExecutor<>();
    private final ServiceExecutor<Integer> rowsCounter = new ServiceExecutor<>();
    private final DateUtil dateUtil = new DateUtil();
    private final TimeUtil timeUtil = new TimeUtil();

    @Override
    public void insertExposition(Map <String, String> parameters) throws ServiceException {
        ExpositionDao expositionDao = factory.getExpositionDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                expositionDao.insertExposition(buildExposition(parameters));
            }
        });
    }

    @Override
    public void updateExposition(Map <String, String> parameters) throws ServiceException {
        ExpositionDao expositionDao = factory.getExpositionDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                expositionDao.updateExposition(buildExpositionWithId(parameters));
            }
        });
    }

    @Override
    public Exposition findExposition(Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Exposition>() {
            @Override
            public Exposition execute() throws DataBaseException, DAOException {
                return expositionDao.findExposition(buildExposition(parameters));
            }
        });
    }

    @Override
    public void deleteExposition(Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                expositionDao.deleteExpositionById(Integer.valueOf(parameters.get("expositionId")));
            }
        });
    }

    @Override
    public List<Exposition> findAll(Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Exposition>() {
            @Override
            public List<Exposition> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return expositionDao.findAll(limit, offset);
            }
        });
    }

    @Override
    public Exposition findExpositionById (Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Exposition>() {
            @Override
            public Exposition execute() throws DataBaseException, DAOException {
                return expositionDao.findExpositionById(Integer.valueOf(parameters.get("expositionId")));
            }
        });
    }

    @Override
    public List<Exposition> findExpositionByShowroomId (Map <String, String> parameters) throws ServiceException{
        ExpositionDao expositionDao = factory.getExpositionDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Exposition>() {
            @Override
            public List<Exposition> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return expositionDao.findExpositionsByShowroomId(Integer.valueOf(parameters.get("showroomId")), limit, offset);
            }
        });
    }

    @Override
    public Integer getNumberOfRows() throws ServiceException {
        ExpositionDao expositionDao = factory.getExpositionDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return expositionDao.getNumberOfRows();
            }
        });
    }

    @Override
    public Integer getNumberOfRowsByShowroomId(Map<String, String> parameters) throws ServiceException {
        ExpositionDao expositionDao = factory.getExpositionDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return expositionDao.getNumberOfRowsByShowroomId(Integer.valueOf(parameters.get("showroomId")));
            }
        });
    }

    private Exposition buildExposition(Map <String, String> parameters){
        return new Exposition.ExpositionBuilder(parameters.get("theme"))
                .setDateStart(dateUtil.formatDate(parameters.get("date"), "yyyy.MM.dd"))
                .setStartTime(timeUtil.formatTime(parameters.get("time"), "HH:mm"))
                .setDescription(parameters.get("expositionDescription"))
                .setShowroom(new Showroom.ShowroomBuilder(parameters.get("showroomName"))
                        .setId(Integer.valueOf(parameters.get("showroomId")))
                        .build())
                .build();
    }

    private Exposition buildExpositionWithId(Map <String, String> parameters){
        return new Exposition.ExpositionBuilder(parameters.get("theme"))
                .setId(Integer.valueOf(parameters.get("expositionId")))
                .setDateStart(dateUtil.formatDate(parameters.get("date"), "yyyy.MM.dd"))
                .setStartTime(timeUtil.formatTime(parameters.get("time"), "HH:mm"))
                .setDescription(parameters.get("expositionDescription"))
                .setShowroom(new Showroom.ShowroomBuilder(parameters.get("showroomName"))
                        .setId(Integer.valueOf(parameters.get("showroomId")))
                        .build())
                .build();
    }
}
