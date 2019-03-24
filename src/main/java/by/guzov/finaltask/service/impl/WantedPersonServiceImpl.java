package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.WantedPersonDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.PaginationTool;
import by.guzov.finaltask.service.AbstractService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.WantedPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WantedPersonServiceImpl extends AbstractService<WantedPerson> implements WantedPersonService {
    private static final Logger LOGGER = LogManager.getLogger(WantedPersonServiceImpl.class);
    private WantedPersonDao wantedPersonDao;

    public WantedPersonServiceImpl() throws ServiceException {
        super.dao = daoInit();
        wantedPersonDao = (WantedPersonDao) super.dao;
    }

    private WantedPersonDao daoInit() throws ServiceException {
        try {
            return (WantedPersonDao) JdbcDaoFactory.getInstance().getDao(WantedPerson.class);
        } catch (DaoException e) {
            LOGGER.error("Failed when dao initialization", e);
            throw new ServiceException("Failed when dao initialization", e);
        }
    }

    @Override
    public List<WantedPerson> getAllByPendingAndStatuses(Boolean pending, String... statuses) throws ServiceException {
        try {
            return wantedPersonDao.getAllByPendingAndStatuses(pending, statuses);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all by pending and statuses", e);
            throw new ServiceException("Failed when getting all by pending and statuses", e);
        }
    }

    @Override
    public List<WantedPerson> getPageByPendingAndStatuses(PaginationTool tool, Boolean pending, String... statuses) throws ServiceException {
        try {
            return wantedPersonDao.getPageByPendingAndStatuses(tool.getCurrentPage(), tool.getAmountOnPage(), pending, statuses);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting page by pending and statuses", e);
            throw new ServiceException("Failed when getting page by pending and statuses", e);
        }
    }

    @Override
    public int countByPendingAndStatuses(Boolean pending, String... statuses) throws ServiceException {
        try {
            return wantedPersonDao.countByPendingAndStatuses(pending,statuses);
        } catch (DaoException e) {
            LOGGER.error("Failed when counting by pending and statuses", e);
            throw new ServiceException("Failed when counting by pending and statuses", e);
        }
    }
}
