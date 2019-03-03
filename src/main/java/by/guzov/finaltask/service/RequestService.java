package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.dto.FullRequest;

import java.util.List;

public interface RequestService {
    Request create(Request request) throws ServiceException;

    void update(Request request) throws ServiceException;

    void delete(Request request) throws ServiceException;

    List<FullRequest> GetAllRequestWithPerson() throws ServiceException;

    FullRequest getFullRequest(int requestID) throws ServiceException;

}
