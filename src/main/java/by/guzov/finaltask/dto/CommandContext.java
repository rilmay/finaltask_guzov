package by.guzov.finaltask.dto;

import java.util.Arrays;
import java.util.List;

public class CommandContext {
    private List<String> allowedUsers;
    private List<String> allowedMethods;

    public List<String> getAllowedUsers() {
        return allowedUsers;
    }

    public CommandContext setAllowedUsers(String... allowedUsers) {
        this.allowedUsers = Arrays.asList(allowedUsers);
        return this;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public CommandContext setAllowedMethods(String... allowedMethods) {
        this.allowedMethods = Arrays.asList(allowedMethods);
        return this;
    }
}
