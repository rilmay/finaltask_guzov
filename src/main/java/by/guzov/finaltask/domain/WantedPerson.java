package by.guzov.finaltask.domain;

import by.guzov.finaltask.dao.Identified;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class WantedPerson implements Identified<Integer>, Serializable {
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
    private int rating;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setIdGeneric(int id) {
        this.id = id;
    }
}
