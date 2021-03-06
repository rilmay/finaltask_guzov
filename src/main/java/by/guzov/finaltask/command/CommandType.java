package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.Restrictions;

import java.util.Optional;
import java.util.stream.Stream;

import static by.guzov.finaltask.util.AppConstants.*;


public enum CommandType {
    AUTHENTICATE_USER(new Restrictions().setMethods(POST).setRoles(ANON)),
    LOG_OUT_USER(new Restrictions().setMethods(GET).setRoles(ADMIN, USER)),
    CHANGE_USER_ROLE(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    DELETE_USER(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    REGISTER_USER(new Restrictions().setMethods(POST).setRoles(ANON)),
    DELETE_PERSONAL_PAGE(new Restrictions().setMethods(POST).setRoles(ADMIN, USER)),
    DELETE_WANTED_PERSON(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    RECOVER_PASSWORD(new Restrictions().setMethods(POST).setRoles(ANON)),
    SEND_REQUEST(new Restrictions().setMethods(POST).setRoles(ADMIN, USER)),
    APPROVE_REQUEST(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    DELETE_REQUEST(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    CANCEL_REQUEST(new Restrictions().setMethods(POST).setRoles(ADMIN, USER)),
    SET_COMPLETED_REQUEST(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    UPLOAD_PHOTO(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    LOAD_IMAGE,
    SEND_RECORD(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    DELETE_RECORD(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    SET_EXPIRED_RECORD(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    SHOW_RECORD_DETAILS,
    SHOW_AUTHENTICATION_PAGE,
    SHOW_REGISTRATION_PAGE,
    SHOW_EMPTY_PAGE,
    SHOW_USER_DETAILS(new Restrictions().setRoles(ADMIN)),
    SHOW_USER_LIST(new Restrictions().setMethods(GET).setRoles(ADMIN)),
    SHOW_PERSONAL_PAGE(new Restrictions().setRoles(ADMIN, USER)),
    SHOW_WANTED_PEOPLE,
    SHOW_PERSON_DETAILS,
    SHOW_RECOVERY_PAGE(new Restrictions().setRoles(ANON)),
    SHOW_REQUEST_FORM(new Restrictions().setRoles(USER, ADMIN)),
    SHOW_REQUEST_LIST,
    SHOW_REQUEST_DETAILS,
    SHOW_PENDING_REQUEST_LIST(new Restrictions().setMethods(GET).setRoles(ADMIN)),
    SHOW_PENDING_PEOPLE(new Restrictions().setMethods(GET).setRoles(ADMIN)),
    SHOW_REQUESTS_BY_USER(new Restrictions().setMethods(GET).setRoles(ADMIN)),
    SHOW_MY_REQUESTS(new Restrictions().setRoles(ADMIN, USER)),
    SHOW_REQUESTS_BY_WANTED_PERSON,
    SHOW_UPLOAD_PHOTO_FORM(new Restrictions().setRoles(ADMIN)),
    SHOW_RECORD_FORM(new Restrictions().setRoles(ADMIN)),
    SHOW_RECORD_LIST,
    SHOW_ABOUT_US_PAGE,
    SHOW_HOW_TO_PAGE;

    public Restrictions getRestrictions() {
        return restrictions;
    }

    private Restrictions restrictions;

    CommandType(Restrictions restrictions) {
        this.restrictions = restrictions;
    }

    CommandType() {
        this.restrictions = new Restrictions();
    }

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }

}
