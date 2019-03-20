package by.guzov.finaltask.service.impl;

import by.guzov.finaltask.command.CommandType;
import by.guzov.finaltask.dao.DaoException;
import by.guzov.finaltask.dao.PersistException;
import by.guzov.finaltask.dao.RecordDao;
import by.guzov.finaltask.dao.impl.JdbcDaoFactory;
import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.i18n.MessageResourceBundle;
import by.guzov.finaltask.i18n.ResourceBundleFactory;
import by.guzov.finaltask.service.RecordService;
import by.guzov.finaltask.service.ServiceException;
import by.guzov.finaltask.service.ServiceFactory;
import by.guzov.finaltask.service.WantedPersonService;
import by.guzov.finaltask.util.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordServiceImpl implements RecordService {
    private static final Logger LOGGER = LogManager.getLogger(RecordServiceImpl.class);
    private RecordDao recordDao;
    private static final String LINK_PATTERN = "@[0-9]+";
    private static final String NOT_FOUND_KEY = "title.not_found";
    private static final String UNKNOWN_KEY = "title.unknown";

    public RecordServiceImpl() throws ServiceException {
        daoInit();
    }

    private void daoInit() throws ServiceException {
        try {
            this.recordDao = (RecordDao) JdbcDaoFactory.getInstance().getDao(Record.class);
        } catch (DaoException e) {
            LOGGER.error("Failed when dao initialization", e);
            throw new ServiceException("Failed when dao initialization", e);
        }
    }

    @Override
    public Record getById(int id) throws ServiceException {
        try {
            return recordDao.getByPK(id);
        } catch (DaoException e) {
            LOGGER.error("Failed when getting by id", e);
            throw new ServiceException("Failed when getting by id", e);
        }
    }

    @Override
    public Record create(Record record) throws ServiceException {
        try {
            return recordDao.persist(record);
        } catch (PersistException e) {
            LOGGER.error("Failed when creating", e);
            throw new ServiceException("Failed when creating", e);
        }
    }

    @Override
    public void delete(Record record) throws ServiceException {
        try {
            recordDao.delete(record);
        } catch (PersistException e) {
            LOGGER.error("Failed when deleting", e);
            throw new ServiceException("Failed when deleting", e);
        }
    }

    @Override
    public void update(Record record) throws ServiceException {
        try {
            recordDao.update(record);
        } catch (PersistException e) {
            LOGGER.error("Failed when updating", e);
            throw new ServiceException("Failed when updating", e);
        }
    }

    @Override
    public List<Record> getAllRelevant() throws ServiceException {
        try {
            return recordDao.getAllRelevant();
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all relevant", e);
            throw new ServiceException("Failed when getting all relevant", e);
        }
    }

    @Override
    public List<Record> getAllExpired() throws ServiceException {
        try {
            return recordDao.getAllExpired();
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all expired", e);
            throw new ServiceException("Failed when getting all expired", e);
        }
    }

    @Override
    public List<Record> getAll() throws ServiceException {
        try {
            return recordDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("Failed when getting all", e);
            throw new ServiceException("Failed when getting all", e);
        }
    }

    @Override
    public void setExpired(Record record) throws ServiceException {
        try {
            record.setRecordStatus("expired");
            recordDao.update(record);
        } catch (PersistException e) {
            LOGGER.error("Failed when setting expired", e);
            throw new ServiceException("Failed when setting expired", e);
        }
    }

    @Override
    public String textWithLinks(String text, String langTag) throws ServiceException {
        MessageResourceBundle bundle = ResourceBundleFactory.getInstance().getBundle(langTag);
        String notFound = bundle.getByKey(NOT_FOUND_KEY);
        String unknown = bundle.getByKey(UNKNOWN_KEY);
        String outText = text;
        WantedPersonService wantedPersonService = ServiceFactory.getInstance().getWantedPersonService();
        Pattern pattern = Pattern.compile(LINK_PATTERN);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String found = matcher.group();
            String link;
            try {
                int id = Integer.parseInt(found.replaceAll("@", ""));
                WantedPerson wantedPerson = wantedPersonService.getById(id);
                String firstName = returnName(wantedPerson.getFirstName(), unknown);
                String lastName = returnName(wantedPerson.getLastName(), unknown);

                link = "<a href=\"?" + AppConstants.COMMAND + "=" + CommandType.SHOW_PERSON_DETAILS
                        + "&" + AppConstants.ID + "=" + id + "\">" + firstName + " " + lastName + "</a>";
            } catch (RuntimeException e) {
                link = "<a>" + notFound + "</a>";
            }
            outText = text.replaceAll(found, link);
        }
        return outText;
    }

    private String returnName(String name, String elseName) {
        if (name != null && !name.isEmpty()) {
            return name;
        } else {
            return elseName;
        }
    }
}
