package by.guzov.finaltask.dto;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;

public class FullRequest {
    private Request request;
    private WantedPerson wantedPerson;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public WantedPerson getWantedPerson() {
        return wantedPerson;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setWantedPerson(WantedPerson wantedPerson) {
        this.wantedPerson = wantedPerson;
    }

    public FullRequest(Request request, WantedPerson wantedPerson) {
        this.request = request;
        this.wantedPerson = wantedPerson;
    }

    public FullRequest(Request request, WantedPerson wantedPerson, User user) {
        this.request = request;
        this.wantedPerson = wantedPerson;
        this.user = user;
    }

    public FullRequest() {
    }
}
