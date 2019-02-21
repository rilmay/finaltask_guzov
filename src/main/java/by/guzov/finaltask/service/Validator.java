package by.guzov.finaltask.service;

import by.guzov.finaltask.dto.ResponseMessage;

public interface Validator<T> {
    ResponseMessage validate(T entity);
}
