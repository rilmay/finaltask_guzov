package by.guzov.finaltask.dao;

import by.guzov.finaltask.domain.WantedPerson;

import java.util.List;

public interface WantedPersonDao extends GenericDao<WantedPerson, Integer> {

    List<WantedPerson> getAllByPendingAndStatuses(Boolean pending, String... statuses) throws DaoException;

    List<WantedPerson> getPageByPendingAndStatuses(int page, int amountOnPage, Boolean pending, String... statuses) throws DaoException;

    int countByPendingAndStatuses(Boolean pending, String... statuses) throws DaoException;
}
