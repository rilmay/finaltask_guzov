package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Request;

public interface RequestService {
    Request create(Request request) throws ServiceException;

    void update(Request request) throws ServiceException;

    void delete(Request request) throws ServiceException;

}
