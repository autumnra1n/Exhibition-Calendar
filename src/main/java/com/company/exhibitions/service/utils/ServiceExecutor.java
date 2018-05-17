package com.company.exhibitions.service.utils;

import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.transaction.TransactionUtil;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.logging.LogManager;

public class ServiceExecutor<T> {

    private final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    public void perform(ServiceExecutable ex) throws ServiceException {
        try {
            ex.execute();
        } catch (DAOException | DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public T performEntitySelect(ServiceEntityExecutable<T> ex) throws ServiceException {
        try {
            return ex.execute();
        } catch (DAOException | DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public List<T> performEntityListSelect(ServiceEntityExecutable<List<T>> ex) throws ServiceException {
        try {
            return ex.execute();
        } catch (DAOException | DataBaseException e) {
            throw new ServiceException(e);
        }
    }

    public void performTransaction(ServiceExecutable ex) throws ServiceException {
        try {
            transactionUtil.beginTransaction();
            ex.execute();
            transactionUtil.commit();
        } catch (DAOException e) {
            try {
                transactionUtil.rollback();
            } catch (DataBaseException exc) {
                throw new ServiceException(e);
            }
        } catch (DataBaseException e) {
            throw new ServiceException(e);
        } finally {
            try {
                transactionUtil.endTransaction();
            } catch (DataBaseException e) {
                throw new ServiceException(e);
            }
        }
    }
}
