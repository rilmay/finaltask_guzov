package by.guzov.finaltask.dao;

import by.guzov.finaltask.domain.WantedPerson;

import java.util.List;

public interface WantedPersonDao extends GenericDao<WantedPerson, Integer> {

    List<WantedPerson> getAllByPendingAndStatuses(Boolean pending, String... statuses) throws DaoException;
}
