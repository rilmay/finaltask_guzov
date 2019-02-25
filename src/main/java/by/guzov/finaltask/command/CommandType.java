package by.guzov.finaltask.command;

import by.guzov.finaltask.dto.CommandContext;
import by.guzov.finaltask.util.ServletConst;

import java.util.Optional;
import java.util.stream.Stream;


public enum CommandType {
    AUTHENTICATE_USER {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(POST).setAllowedUsers(ANON);
        }
    },
    LOG_OUT_USER {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(POST, GET).setAllowedUsers(ADMIN, USER);
        }
    },
    CHANGE_USER_ROLE {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(POST).setAllowedUsers(ADMIN);
        }
    },
    DELETE_USER {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(POST).setAllowedUsers(ADMIN);
        }
    },
    REGISTER_USER {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(POST).setAllowedUsers(ANON);
        }
    },
    DELETE_PERSONAL_PAGE {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(POST).setAllowedUsers(ADMIN, USER);
        }
    },
    SHOW_AUTHENTICATION_PAGE,
    SHOW_REGISTRATION_PAGE,
    SHOW_EMPTY_PAGE,
    SHOW_USER_DETAILS {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(GET, POST).setAllowedUsers(ADMIN, USER);
        }
    },
    SHOW_USER_LIST {
        @Override
        public CommandContext getRestrictions() {
            return new CommandContext().setAllowedMethods(GET).setAllowedUsers(ADMIN, USER);
        }
    },
    SHOW_ERROR_PAGE,
    SHOW_PERSONAL_PAGE {
        @Override
        public CommandContext getRestrictions() {
            return super.getRestrictions().setAllowedUsers(ADMIN, USER);
        }
    },
    SHOW_WANTED_PEOPLE,
    SHOW_PERSON_DETAILS;

    public CommandContext getRestrictions() {
        return new CommandContext().setAllowedMethods(GET, POST).setAllowedUsers(ALL_USERS);
    }

    private static final String GET = ServletConst.GET;
    private static final String POST = ServletConst.POST;
    private static final String ADMIN = ServletConst.ADMIN;
    private static final String USER = ServletConst.USER;
    private static final String ANON = ServletConst.ANON;
    private static final String[] ALL_USERS = new String[]{USER, ADMIN, ANON};

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }

}
