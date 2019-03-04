package by.guzov.finaltask.command;

import by.guzov.finaltask.command.admin.*;

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
        commandMap.put(CommandType.SHOW_ERROR_PAGE, new CommandShowErrorPage());
        commandMap.put(CommandType.SHOW_PERSONAL_PAGE, new CommandShowPersonalPage());
        commandMap.put(CommandType.DELETE_PERSONAL_PAGE, new CommandDeletePersonalPage());
        commandMap.put(CommandType.SHOW_WANTED_PEOPLE, new CommandShowWantedPeople());
        commandMap.put(CommandType.SHOW_PERSON_DETAILS, new CommandShowPersonDetails());
        commandMap.put(CommandType.SHOW_RECOVERY_PAGE, new CommandShowRecoveryPage());
        commandMap.put(CommandType.RECOVER_PASSWORD, new CommandRecoverPassword());
        commandMap.put(CommandType.SHOW_SUCCESS_PAGE, new CommandShowSuccessPage());
        commandMap.put(CommandType.SHOW_REQUEST_FORM, new CommandShowRequestForm());
        commandMap.put(CommandType.SEND_REQUEST, new CommandSendRequest());
        commandMap.put(CommandType.DELETE_WANTED_PERSON, new CommandDeleteWantedPerson());
        commandMap.put(CommandType.SHOW_REQUEST_LIST, new CommandShowRequestList());
        commandMap.put(CommandType.SHOW_REQUEST_DETAILS, new CommandShowRequestDetails());
        commandMap.put(CommandType.SHOW_PENDING_REQUEST_LIST, new CommandShowPendingRequestList());
        commandMap.put(CommandType.SHOW_PENDING_PEOPLE, new CommandShowPendingPeople());
        commandMap.put(CommandType.APPROVE_REQUEST, new CommandApproveRequest());
        commandMap.put(CommandType.DELETE_REQUEST, new CommandDeleteRequest());
        commandMap.put(CommandType.CANCEL_REQUEST, new CommandCancelRequest());
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
