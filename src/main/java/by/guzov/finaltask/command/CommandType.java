package by.guzov.finaltask.command;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * CommandType
 * <p>
 * <p>
 * getProperty()
 * 1 - GET/POST ALL USERS
 * 2 - GET ALL REGISTERED USERS
 * 3 - POST ALL REGISTERED USERS
 * 4 - GET/POST ADMIN ONLY
 */

public enum CommandType {
    CHANGE_USER_ROLE {
        @Override
        int getProperty() {
            return 4;
        }
    }, DELETE_USER {
        @Override
        int getProperty() {
            return 4;
        }
    }, REGISTER_USER {
        @Override
        int getProperty() {
            return 1;
        }
    }, SHOW_REGISTRATION_PAGE {
        @Override
        int getProperty() {
            return 1;
        }
    }, SHOW_EMPTY_PAGE {
        @Override
        int getProperty() {
            return 1;
        }
    }, SHOW_USER_DETAILS {
        @Override
        int getProperty() {
            return 4;
        }
    }, SHOW_USER_LIST {
        @Override
        int getProperty() {
            return 4;
        }
    };

    int getProperty() {
        return 4;
    }

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }

}
