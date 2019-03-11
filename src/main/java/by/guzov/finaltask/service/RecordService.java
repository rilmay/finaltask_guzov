package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Record;

import java.util.List;

public interface RecordService {
    Record getById(int id);

    Record create(Record record);

    void delete(Record record);

    void update(Record record);

    List<Record> getAllRelevant();

    List<Record> getAllExpired();

    List<Record> getAll();
}
