package by.guzov.finaltask.domain.Builder;

import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.util.FieldNames;

import java.util.Map;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(Map<String, String> fieldMap) {
        String login = fieldMap.get(FieldNames.LOGIN);
        String password = fieldMap.get(FieldNames.PASSWORD);
        String email = fieldMap.get(FieldNames.EMAIL);
        String firstName = fieldMap.get(FieldNames.FIRST_NAME);
        String lastName = fieldMap.get(FieldNames.LAST_NAME);

        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
