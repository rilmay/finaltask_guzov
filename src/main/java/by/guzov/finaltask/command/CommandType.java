package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.CommandContext;

import java.util.Optional;
import java.util.stream.Stream;


public enum CommandType {
    AUTHENTICATE_USER,
    LOG_OUT_USER,
    CHANGE_USER_ROLE,
    DELETE_USER {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(POST).setAllowedUsers(ADMIN);
        }
    },
    REGISTER_USER,
    SHOW_AUTHENTICATION_PAGE,
    SHOW_REGISTRATION_PAGE,
    SHOW_EMPTY_PAGE,
    SHOW_USER_DETAILS {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(GET, POST).setAllowedUsers(ADMIN, USER);
        }
    }, SHOW_USER_LIST;


    public CommandContext getRestrictions() {
        return new CommandContext().setAllowedMethods(GET, POST).setAllowedUsers(ALL_USERS);
    }

    private static final String GET = "get";
    private static final String POST = "post";
    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String ANON = "null";


    private static final String[] ALL_USERS = new String[]{USER, ADMIN, ANON};

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }

}
