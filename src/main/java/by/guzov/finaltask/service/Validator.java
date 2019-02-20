package by.guzov.finaltask.service;

import by.guzov.finaltask.dto.ResponceMessage;

public interface Validator<T> {
    ResponceMessage validate(T entity);
}
