package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.WantedPerson;
import by.guzov.finaltask.dto.PaginationTool;

import java.util.List;

public interface WantedPersonService extends GenericService<WantedPerson> {
    List<WantedPerson> getAllByPendingAndStatuses(Boolean pending, String... statuses) throws ServiceException;

    List<WantedPerson> getPageByPendingAndStatuses(PaginationTool tool, Boolean pending, String... statuses) throws ServiceException;

    int countByPendingAndStatuses(Boolean pending, String... statuses) throws ServiceException;
}
