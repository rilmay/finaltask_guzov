package by.guzov.finaltask.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<CommandType, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put(null, new CommandShowEmptyPage());
        commandMap.put(CommandType.SHOW_USER_LIST, new CommandShowUserList());
        commandMap.put(CommandType.SHOW_USER_DETAILS, new CommandShowUserDetails());
        commandMap.put(CommandType.DELETE_USER, new CommandDeleteUser());
        commandMap.put(CommandType.CHANGE_USER_ROLE, new CommandChangeUserRole());
        commandMap.put(CommandType.SHOW_REGISTRATION_PAGE, new CommandShowRegistrationPage());
        commandMap.put(CommandType.REGISTER_USER, new CommandRegisterUser());
        commandMap.put(CommandType.SHOW_EMPTY_PAGE, new CommandShowEmptyPage());
        commandMap.put(CommandType.AUTHENTICATE_USER, new CommandAuthenticateUser());
        commandMap.put(CommandType.SHOW_AUTHENTICATION_PAGE, new CommandShowAuthenticationPage());
        commandMap.put(CommandType.LOG_OUT_USER, new CommandLogOutUser());
    }

    /**
     * Return command by name
     *
     * @param commandType name
     * @return command implementation
     */
    public Command takeCommand(CommandType commandType) {
        return commandMap.get(commandType);
    }
}
