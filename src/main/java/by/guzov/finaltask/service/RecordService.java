package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.dto.PaginationTool;

import java.util.List;

public interface RecordService extends GenericService<Record> {
    List<Record> getAllRelevant() throws ServiceException;

    List<Record> getAllExpired() throws ServiceException;

    void setExpired(Record record) throws ServiceException;

    String textWithLinks(String text, String langTag) throws ServiceException;

    List<Record> getPageRelevant(PaginationTool tool) throws ServiceException;

    List<Record> getPageExpired(PaginationTool tool) throws ServiceException;

    int countRelevant() throws ServiceException;

    int countExpired() throws ServiceException;
}
