package by.guzov.finaltask.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PasswordRecovery {
    String code;
    long expires;
    int userId;
}
