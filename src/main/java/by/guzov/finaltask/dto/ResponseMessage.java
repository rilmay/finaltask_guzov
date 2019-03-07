package by.guzov.finaltask.dto;

public class ResponseMessage {
    private boolean valid;
    private String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
}
