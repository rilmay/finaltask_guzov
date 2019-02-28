package by.guzov.finaltask.domain;

import by.guzov.finaltask.dao.Identified;
import lombok.Data;

import java.sql.Date;

@Data
public class WantedPerson implements Identified<Integer> {
    private int id;
    private String firstName;
    private String lastName;
    private String personStatus;
    private String description;
    private String birthPlace;
    private Date birthDate;
    private String searchArea;
    private String specialSigns;
    private String photo;
    private boolean pending;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setIDGen(int id) {
        this.id = id;
    }
}
