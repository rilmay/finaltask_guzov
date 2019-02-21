package by.guzov.finaltask.dto;

public class ResponseMessage {
    private boolean answer;
    private String message;

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(boolean answer, String message) {
        this.answer = answer;
        this.message = message;
    }
}
