package by.guzov.finaltask.service;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.GenericDao;
import by.guzov.finaltask.dao.Identified;
import by.guzov.finaltask.dao.PersistException;
import by.guzov.finaltask.dto.PaginationTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class AbstractService<T extends Identified<Integer>> implements GenericService<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractService.class);
    protected GenericDao<T, Integer> dao;

    @Override
    public T getById(int id) throws ServiceException {
        try {
            return dao.getByPK(id);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting by id", e);
            throw new ServiceException("Failed when getting by id", e);
        }
    }

    @Override
    public T create(T entity) throws ServiceException {
        try {
            return dao.persist(entity);
        } catch (PersistException e) {
            LOGGER.error("Failed when creating", e);
            throw new ServiceException("Failed when creating", e);
        }
    }

    @Override
    public void update(T entity) throws ServiceException {
        try {
            dao.update(entity);
        } catch (PersistException e) {
            LOGGER.error("Failed when updating", e);
            throw new ServiceException("Failed when updating", e);
        }
    }

    @Override
    public void delete(T entity) throws ServiceException {
        try {
            dao.delete(entity);
        } catch (PersistException e) {
            LOGGER.error("Failed when deleting", e);
            throw new ServiceException("Failed when deleting", e);
        }
    }

    @Override
    public List<T> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all", e);
            throw new ServiceException("Failed when getting all", e);
        }
    }

    @Override
    public int recordCount() throws ServiceException {
        try {
            return dao.recordCount();
        } catch (DaoException e) {
            LOGGER.error("Failed when counting", e);
            throw new ServiceException("Failed when counting", e);
        }
    }

    @Override
    public List<T> getAllByPage(PaginationTool paginationTool) throws ServiceException {
        try {
            return dao.getAllByPage(paginationTool.getCurrentPage(), paginationTool.getAmountOnPage());
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all by page", e);
            throw new ServiceException("Failed when getting all by page", e);
        }
    }
}
