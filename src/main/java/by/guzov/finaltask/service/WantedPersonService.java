package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.WantedPerson;

import java.util.List;

public interface WantedPersonService{
    List<WantedPerson> getAll();
    List<WantedPerson> getAllExceptPending();
    List<WantedPerson> getAllPending();
    void delete(WantedPerson wantedPerson);
    WantedPerson getById(int id);
    void update(WantedPerson wantedPerson);
    WantedPerson create(WantedPerson wantedPerson);
}
