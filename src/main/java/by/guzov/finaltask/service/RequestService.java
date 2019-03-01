package by.guzov.finaltask.service;

import by.guzov.finaltask.domain.Request;

public interface RequestService {
    Request create(Request request);

    void update(Request request);

    void delete(Request request);

}
