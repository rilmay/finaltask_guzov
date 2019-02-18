package by.guzov.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<String, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put("CommandExample", new CommandExample());
        commandMap.put(null, new CommandViewEmptyAdminPage());
        commandMap.put("user_list", new CommandViewUserList());
        commandMap.put("view_user_details", new CommandViewUserDetails());
        commandMap.put("delete_user", new CommandDeleteUser());
        commandMap.put("update_user_role", new CommandChangeUserRole());
        commandMap.put("show_registration_page", new CommandShowRegistrationPage());
        commandMap.put("register_user", new CommandRegisterUser());
    }

    /**
     * Return command by name
     *
     * @param command name
     * @return command implementation
     */
    public Command takeCommand(String command) {
        return commandMap.get(command);
    }
}
