package com.company.exhibitions.service;

import com.company.exhibitions.dto.Exposition;
import com.company.exhibitions.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface IExpositionService {

    void insertExposition(Map<String, String> parameters) throws ServiceException;
    void updateExposition(Map <String, String> parameters) throws ServiceException;
    void deleteExposition(Map <String, String> parameters) throws ServiceException;
    Exposition findExposition(Map <String, String> parameters) throws ServiceException;
    List<Exposition> findAll(Map <String, String> parameters) throws ServiceException;
    Exposition findExpositionById (Map <String, String> parameters) throws ServiceException;
    List<Exposition> findExpositionByShowroomId (Map <String, String> parameters) throws ServiceException;
    Integer getNumberOfRows() throws ServiceException;
    Integer getNumberOfRowsByShowroomId(Map <String, String> parameters) throws ServiceException;
}
