package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Record;

import java.util.List;

public interface RecordService {
    Record getById(int id) throws ServiceException;

    Record create(Record record) throws ServiceException;

    void delete(Record record) throws ServiceException;

    void update(Record record) throws ServiceException;

    List<Record> getAllRelevant() throws ServiceException;

    List<Record> getAllExpired() throws ServiceException;

    List<Record> getAll() throws ServiceException;

    void setExpired(Record record) throws ServiceException;
}
