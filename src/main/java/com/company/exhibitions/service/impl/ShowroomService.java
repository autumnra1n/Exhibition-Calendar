package com.company.exhibitions.service.impl;

import com.company.exhibitions.dao.DaoFactory;
import com.company.exhibitions.dao.ShowroomDao;
import com.company.exhibitions.dao.mysql.MySqlDaoFactory;
import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IShowroomService;
import com.company.exhibitions.service.utils.ServiceExecutable;
import com.company.exhibitions.service.utils.ServiceExecutor;
import com.company.exhibitions.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowroomService implements IShowroomService {

    private final DaoFactory factory = Mapper.getDaoFactory();
    private final ServiceExecutor<Showroom> executor = new ServiceExecutor<>();

    @Override
    public void insertShowroom(Map<String, String> parameters) throws ServiceException {
        ShowroomDao showroomDao = factory.getShowroomDao();
        executor.perform(() -> showroomDao.insertShowroom(buildShowroom(parameters)));
    }

    @Override
    public void updateShowroom(Map <String, String> parameters) throws ServiceException {
        ShowroomDao showroomDao = factory.getShowroomDao();
        executor.perform(() -> showroomDao.updateShowroom(buildShowroomWithId(parameters)));
    }

    @Override
    public void deleteShowroom(Map <String, String> parameters) throws ServiceException{
        ShowroomDao showroomDao = factory.getShowroomDao();
        executor.perform(() -> showroomDao.deleteShowroomById(Integer.valueOf(parameters.get("id"))));
    }

    @Override
    public Showroom findShowroom(Map <String, String> parameters) throws ServiceException{
        ShowroomDao showroomDao = factory.getShowroomDao();
        return executor.performEntitySelect(() -> showroomDao.findShowroom(buildShowroom(parameters)));
    }

    @Override
    public List<Showroom> findAll() throws ServiceException{
        ShowroomDao showroomDao = factory.getShowroomDao();
        return executor.performEntityListSelect(showroomDao::findAll);
    }

    @Override
    public Showroom findShowroomById (Map <String, String> parameters) throws ServiceException {
        ShowroomDao showroomDao = factory.getShowroomDao();
        return executor.performEntitySelect(() -> showroomDao.findShowroomById(Integer.valueOf(parameters.get("id"))));
    }

    private Showroom buildShowroom(Map <String, String> parameters){
        return new Showroom.ShowroomBuilder(parameters.get("name"))
                .setDescription(parameters.get("description"))
                .setLocation(parameters.get("location"))
                .build();
    }

    private Showroom buildShowroomWithId(Map <String, String> parameters){
        return new Showroom.ShowroomBuilder(parameters.get("name"))
                .setDescription(parameters.get("description"))
                .setLocation(parameters.get("location"))
                .setId(Integer.valueOf(parameters.get("id")))
                .build();
    }
}

