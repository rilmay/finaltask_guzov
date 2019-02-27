package by.guzov.finaltask.dto;

import lombok.Data;

@Data
public class PasswordRecovery {
    String code;
    long expires;
    int userId;
}
