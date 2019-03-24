package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.dto.FullRequest;
import by.guzov.finaltask.dto.PaginationTool;

import java.util.List;

public interface RequestService extends GenericService<Request> {

    FullRequest getFullRequest(int requestID) throws ServiceException;

    void approve(Request request) throws ServiceException;

    void cancel(Request request) throws ServiceException;

    void setCompleted(Request request) throws ServiceException;

    List<FullRequest> getPageByWantedPersonAndStatuses(PaginationTool tool, Integer wantedPersonId, String... statuses) throws ServiceException;

    List<FullRequest> getPageByUserAndStatuses(PaginationTool tool, Integer userId, String... statuses) throws ServiceException;

    int countByWantedPersonAndStatuses(Integer wantedPersonId, String... statuses) throws ServiceException;

    int countByUserAndStatuses(Integer userId, String... statuses) throws ServiceException;

}
