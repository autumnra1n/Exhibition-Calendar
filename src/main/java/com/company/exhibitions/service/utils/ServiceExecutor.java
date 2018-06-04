package com.company.exhibitions.service.utils;

import com.company.exhibitions.exception.DAOException;
import com.company.exhibitions.exception.DataBaseException;
import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.transaction.TransactionUtil;
import com.company.exhibitions.transaction.Transactional;
import com.company.exhibitions.utils.Mapper;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.LogManager;

public class ServiceExecutor<T> {

    private final TransactionUtil transactionUtil = Mapper.getTransactionUtil();
    private final TransactionChecker<T> transactionChecker = new TransactionChecker<>();

    public void perform(ServiceExecutable ex) throws ServiceException {
        if(transactionChecker.isTransactional(ex)){
            performTransaction(ex);
        }
        else {
            try {
                ex.execute();
            } catch (DAOException | DataBaseException e) {
                throw new ServiceException(e);
            }
        }
    }

    public T performEntitySelect(ServiceEntityExecutable<T> ex) throws ServiceException {
        if(transactionChecker.isEntitySelectTransactional(ex)){
            return performTransactionalEntitySelect(ex);
        }
        else {
            try {
                return ex.execute();
            } catch (DAOException | DataBaseException e) {
                throw new ServiceException(e);
            }
        }
    }

    public List<T> performEntityListSelect(ServiceEntityListExecutable<T> ex) throws ServiceException {
        if(transactionChecker.isEntityListSelectTransactional(ex)){
            return performTransactionalEntityListSelect(ex);
        }
        else {
            try {
                return ex.execute();
            } catch (DAOException | DataBaseException e) {
                throw new ServiceException(e);
            }
        }
    }

    private void performTransaction(ServiceExecutable ex) throws ServiceException {
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

    private T performTransactionalEntitySelect(ServiceEntityExecutable<T> ex) throws ServiceException{
        T result = null;
        try {
            transactionUtil.beginTransaction();
            result = ex.execute();
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
        return result;
    }

    private List<T> performTransactionalEntityListSelect(ServiceEntityListExecutable<T> ex) throws ServiceException {
        List<T> list = null;
        try {
            transactionUtil.beginTransaction();
            list = ex.execute();
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
        return list;
    }
}
