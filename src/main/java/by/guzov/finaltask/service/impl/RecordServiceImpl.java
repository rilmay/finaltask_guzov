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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordServiceImpl implements RecordService {
    private RecordDao recordDao;
    private static final String LINK_PATTERN = "@[0-9]+";

    public RecordServiceImpl() throws ServiceException {
        daoInit();
    }

    private void daoInit() throws ServiceException {
        try {
            this.recordDao = (RecordDao) JdbcDaoFactory.getInstance().getDao(Record.class);
        } catch (DaoException e) {
            throw new ServiceException("Server error", e);
        }
    }

    @Override
    public Record getById(int id) throws ServiceException {
        try {
            return recordDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public Record create(Record record) throws ServiceException {
        try {
            return recordDao.persist(record);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void delete(Record record) throws ServiceException {
        try {
            recordDao.delete(record);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void update(Record record) throws ServiceException {
        try {
            recordDao.update(record);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }

    }

    @Override
    public List<Record> getAllRelevant() throws ServiceException {
        try {
            return recordDao.getAllRelevant();
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public List<Record> getAllExpired() throws ServiceException {
        try {
            return recordDao.getAllExpired();
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public List<Record> getAll() throws ServiceException {
        try {
            return recordDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public void setExpired(Record record) throws ServiceException {
        try {
            record.setRecordStatus("expired");
            recordDao.update(record);
        } catch (PersistException e) {
            throw new ServiceException("server error", e);
        }
    }

    @Override
    public String textWithLinks(String text, String langTag) throws ServiceException {
        MessageResourceBundle bundle = ResourceBundleFactory.getInstance().getBundle(langTag);
        String notFound = bundle.getByKey("title.not_found");
        String unknown = bundle.getByKey("title.unknown");
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
                link = "<a href=\"#\">" + notFound + "</a>";
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
