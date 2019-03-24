package by.guzov.finaltask.dao;

import by.guzov.finaltask.domain.Record;

import java.util.List;

public interface RecordDao extends GenericDao<Record, Integer> {
    List<Record> getAllRelevant() throws DaoException;

    List<Record> getAllExpired() throws DaoException;

    List<Record> getPageRelevant(int page, int amountOnPage) throws DaoException;

    List<Record> getPageExpired(int page, int amountOnPage) throws DaoException;

    int countRelevant() throws DaoException;

    int countExpired() throws DaoException;
}
