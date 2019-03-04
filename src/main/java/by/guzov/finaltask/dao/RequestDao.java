package by.guzov.finaltask.dao;

import by.guzov.finaltask.dao.exception.DaoException;
import by.guzov.finaltask.domain.Request;

import java.util.List;

public interface RequestDao extends GenericDao<Request, Integer> {
    List<Request> getAllPending() throws DaoException;

    List<Request> getAllExceptPending() throws DaoException;
}
