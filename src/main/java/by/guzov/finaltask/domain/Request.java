package by.guzov.finaltask.domain;

import by.guzov.finaltask.dao.Identified;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class Request implements Identified<Integer>, Serializable {
    private int id;
    private int reward;
    private Date applicationDate;
    private Date leadDate;
    private String requestStatus;
    private int wantedPersonId;
    private int userId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setIdGeneric(int id) {
        this.id = id;
    }
}
