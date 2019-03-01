package by.guzov.finaltask.util.validation;

import by.guzov.finaltask.dao.Identified;
import by.guzov.finaltask.dto.ResponseMessage;

public interface EntityValidator<T extends Identified> {
    ResponseMessage validate(T entity);
}
