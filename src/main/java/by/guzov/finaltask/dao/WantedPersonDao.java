package by.guzov.finaltask.dao;

import by.guzov.finaltask.domain.WantedPerson;

import java.util.List;

public interface WantedPersonDao extends GenericDao<WantedPerson, Integer> {
    List<WantedPerson> getAllPending() throws DaoException;

    List<WantedPerson> getAllExceptPending() throws DaoException;
}
