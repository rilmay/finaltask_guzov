package by.guzov.finaltask.dto;

import by.guzov.finaltask.domain.Request;

public class FullRequest {
    private Request request;
    private String personFirstName;
    private String personLastName;
    private String userLogin;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public FullRequest(Request request, String personFirstName, String personLastName) {
        this.request = request;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
    }

    public FullRequest(Request request, String personFirstName, String personLastName, String userLogin) {
        this.request = request;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.userLogin = userLogin;
    }

    public FullRequest() {
    }
}
