package by.guzov.finaltask.dto;

import java.util.Arrays;
import java.util.List;

public class CommandContext {
    private List<String> allowedUsers;
    private List<String> allowedMethods;

    public boolean isAllowedUser(String role) {
        return allowedUsers.contains(role);
    }

    public boolean isAllowedMethod(String method) {
        return allowedMethods.contains(method);
    }

    public CommandContext setAllowedUsers(String... allowedUsers) {
        this.allowedUsers = Arrays.asList(allowedUsers);
        return this;
    }

    public CommandContext setAllowedMethods(String... allowedMethods) {
        this.allowedMethods = Arrays.asList(allowedMethods);
        return this;
    }
}
