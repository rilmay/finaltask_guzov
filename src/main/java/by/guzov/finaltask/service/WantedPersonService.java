package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.WantedPerson;

import java.util.List;

public interface WantedPersonService {
    List<WantedPerson> getAll() throws ServiceException;

    List<WantedPerson> getAllExceptPending() throws ServiceException;

    List<WantedPerson> getAllPending() throws ServiceException;

    void delete(WantedPerson wantedPerson) throws ServiceException;

    WantedPerson getById(int id) throws ServiceException;

    void update(WantedPerson wantedPerson) throws ServiceException;

    WantedPerson create(WantedPerson wantedPerson) throws ServiceException;
}
