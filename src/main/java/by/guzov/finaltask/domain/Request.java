package by.guzov.finaltask.domain;

import by.guzov.finaltask.dao.Identified;
import lombok.Data;

import java.sql.Date;

@Data
public class Request implements Identified<Integer> {
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
    public void setIDGen(int id) {
        this.id = id;
    }
}
