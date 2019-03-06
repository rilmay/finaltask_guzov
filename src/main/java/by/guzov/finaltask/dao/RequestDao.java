package by.guzov.finaltask.dao;

import by.guzov.finaltask.domain.Request;

import java.util.List;

public interface RequestDao extends GenericDao<Request, Integer> {

    List<Request> getAllByWantedPersonAndStatus(Integer personId, String... requestStatuses) throws DaoException;

    List<Request> getAllByUserAndStatus(Integer userId, String... requestStatuses) throws DaoException;
}
