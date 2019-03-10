package by.guzov.finaltask.domain.Builder;

import by.guzov.finaltask.dao.Identified;

import java.util.Map;

public interface Builder<T extends Identified> {
    T build(Map<String, String> fieldMap);
}
