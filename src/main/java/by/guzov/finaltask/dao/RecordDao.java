package by.guzov.finaltask.dao;

import by.guzov.finaltask.domain.Record;

import java.util.List;

public interface RecordDao extends GenericDao<Record, Integer> {
    List<Record> getAllRelevant() throws DaoException;

    List<Record> getAllExpired() throws DaoException;
}
