package com.company.exhibitions.service;

import com.company.exhibitions.dto.Showroom;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IShowroomService {

    void insertShowroom(Map<String, String> parameters) throws ServiceException;
    void updateShowroom(Map <String, String> parameters) throws ServiceException;
    void deleteShowroom(Map <String, String> parameters) throws ServiceException;
    Showroom findShowroom(Map <String, String> parameters) throws ServiceException;
    List<Showroom> findAll(Map <String, String> parameters) throws ServiceException;
    Showroom findShowroomById (Map <String, String> parameters) throws ServiceException;
    Integer getNumberOfRows()throws ServiceException;
}
