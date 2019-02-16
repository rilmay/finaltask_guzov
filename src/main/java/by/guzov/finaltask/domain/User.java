package by.guzov.finaltask.domain;

import by.guzov.finaltask.dao.Identified;
import lombok.Data;

import java.sql.Date;

@Data
public class User implements Identified<Integer> {
    private int id;
    private String login;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private Date registrationDate;
    private String email;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setIDGen(int id) {
        this.id = id;
    }
}
