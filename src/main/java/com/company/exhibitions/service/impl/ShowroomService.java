package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.RoleDao;
import com.company.exhibitions.dao.ShowroomDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.utils.ServiceEntityExecutable;
import com.company.exhibitions.service.utils.ServiceEntityListExecutable;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowroomService implements IShowroomService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Showroom> executor = new ServiceExecutor<>();
    private final ServiceExecutor<Integer> rowsCounter = new ServiceExecutor<>();

    @Override
    public void insertShowroom(Map<String, String> parameters) throws ServiceException {
        ShowroomDao showroomDao = factory.getShowroomDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                showroomDao.insertShowroom(buildShowroom(parameters));
            }
        });
    }

    @Override
    public void updateShowroom(Map <String, String> parameters) throws ServiceException {
        ShowroomDao showroomDao = factory.getShowroomDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                showroomDao.updateShowroom(buildShowroomWithId(parameters));
            }
        });
    }

    @Override
    public void deleteShowroom(Map <String, String> parameters) throws ServiceException{
        ShowroomDao showroomDao = factory.getShowroomDao();
        executor.perform(new ServiceExecutable() {
            @Override
            public void execute() throws DataBaseException, DAOException {
                showroomDao.deleteShowroomById(Integer.valueOf(parameters.get("showroomId")));
            }
        });
    }

    @Override
    public Showroom findShowroom(Map <String, String> parameters) throws ServiceException{
        ShowroomDao showroomDao = factory.getShowroomDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Showroom>() {
            @Override
            public Showroom execute() throws DataBaseException, DAOException {
                return showroomDao.findShowroom(buildShowroom(parameters));
            }
        });
    }

    @Override
    public List<Showroom> findAll(Map <String, String> parameters) throws ServiceException{
        ShowroomDao showroomDao = factory.getShowroomDao();
        return executor.performEntityListSelect(new ServiceEntityListExecutable<Showroom>() {
            @Override
            public List<Showroom> execute() throws DataBaseException, DAOException {
                int limit = Integer.valueOf(parameters.get("limit"));
                int offset = Integer.valueOf(parameters.get("currentPage")) * limit - limit;
                return showroomDao.findAll(limit, offset);
            }
        });
    }

    @Override
    public Showroom findShowroomById (Map <String, String> parameters) throws ServiceException {
        ShowroomDao showroomDao = factory.getShowroomDao();
        return executor.performEntitySelect(new ServiceEntityExecutable<Showroom>() {
            @Override
            public Showroom execute() throws DataBaseException, DAOException {
                return showroomDao.findShowroomById(Integer.valueOf(parameters.get("showroomId")));
            }
        });
    }

    @Override
    public Integer getNumberOfRows() throws ServiceException {
        ShowroomDao showroomDao = factory.getShowroomDao();
        return rowsCounter.performEntitySelect(new ServiceEntityExecutable<Integer>() {
            @Override
            public Integer execute() throws DataBaseException, DAOException {
                return showroomDao.getNumberOfRows();
            }
        });
    }

    private Showroom buildShowroom(Map <String, String> parameters){
        return new Showroom.ShowroomBuilder(parameters.get("name"))
                .setDescription(parameters.get("showroomDescription"))
                .setLocation(parameters.get("location"))
                .build();
    }

    private Showroom buildShowroomWithId(Map <String, String> parameters){
        return new Showroom.ShowroomBuilder(parameters.get("name"))
                .setDescription(parameters.get("showroomDescription"))
                .setLocation(parameters.get("location"))
                .setId(Integer.valueOf(parameters.get("showroomId")))
                .build();
    }
}

