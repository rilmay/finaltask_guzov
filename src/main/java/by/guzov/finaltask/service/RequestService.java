package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.dto.RequestCondition;

import java.util.List;

public interface RequestService {
    Request getById(int requestId) throws ServiceException;

    Request create(Request request) throws ServiceException;

    void update(Request request) throws ServiceException;

    void delete(Request request) throws ServiceException;

    List<FullRequest> getAllFullRequests() throws ServiceException;

    FullRequest getFullRequest(int requestID) throws ServiceException;

    List<FullRequest> getAllWithCondition(RequestCondition condition) throws ServiceException;

    void approve(Request request) throws ServiceException;

    void cancel(Request request) throws ServiceException;

    void setCompleted(Request request) throws ServiceException;
}
