package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.Restrictions;

import java.util.Optional;
import java.util.stream.Stream;

import static by.guzov.finaltask.util.AppConstants.*;


public enum CommandType {
    AUTHENTICATE_USER(new Restrictions().setMethods(POST).setRoles(ANON)),
    LOG_OUT_USER(new Restrictions().setRoles(ADMIN, USER)),
    CHANGE_USER_ROLE(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    DELETE_USER(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    REGISTER_USER(new Restrictions().setMethods(POST).setRoles(ANON)),
    DELETE_PERSONAL_PAGE(new Restrictions().setMethods(POST).setRoles(ADMIN, USER)),
    DELETE_WANTED_PERSON(new Restrictions().setMethods(POST).setRoles(ADMIN)),
    RECOVER_PASSWORD(new Restrictions().setMethods(POST).setRoles(ANON)),
    SEND_REQUEST(new Restrictions().setMethods(POST).setRoles(ADMIN, USER)),
    SHOW_AUTHENTICATION_PAGE,
    SHOW_REGISTRATION_PAGE,
    SHOW_EMPTY_PAGE,
    SHOW_USER_DETAILS(new Restrictions().setRoles(ADMIN)),
    SHOW_USER_LIST(new Restrictions().setMethods(GET).setRoles(ADMIN)),
    SHOW_ERROR_PAGE,
    SHOW_PERSONAL_PAGE(new Restrictions().setRoles(ADMIN, USER)),
    SHOW_WANTED_PEOPLE,
    SHOW_PERSON_DETAILS,
    SHOW_RECOVERY_PAGE(new Restrictions().setRoles(ANON)),
    SHOW_SUCCESS_PAGE,
    SHOW_REQUEST_FORM(new Restrictions().setRoles(USER, ADMIN)),
    SHOW_REQUEST_LIST,
    SHOW_REQUEST_DETAILS,
    SHOW_PENDING_REQUEST_LIST(new Restrictions().setRoles(ADMIN)),
    SHOW_PENDING_PEOPLE(new Restrictions().setRoles(ADMIN));

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
