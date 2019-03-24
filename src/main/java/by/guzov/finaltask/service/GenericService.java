package by.guzov.finaltask.service;

import by.guzov.finaltask.dto.PaginationTool;

import java.util.List;

public interface GenericService<T> {
    T getById(int id) throws ServiceException;

    T create(T entity) throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(T entity) throws ServiceException;

    List<T> getAll() throws ServiceException;

    int recordCount() throws ServiceException;

    List<T> getAllByPage(PaginationTool paginationTool) throws ServiceException;
}
