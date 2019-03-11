package by.guzov.finaltask.domain;

import by.guzov.finaltask.dao.Identified;
import lombok.Data;

import java.sql.Date;

@Data
public class Record implements Identified<Integer> {
    private int id;
    private String description;
    private String place;
    private Date date;
    private String recordStatus;
    private int rating;
    private String name;
    private String photo;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setIDGen(int id) {
        this.id = id;
    }
}
