package by.guzov.finaltask.dto;



public enum RequestCondition{
    APPROVED("request_status = 'approved'"),
    PENDING("request_status = 'pending'"),
    COMPLETED("request_status = 'completed'"),
    CANCELLED("request_status = 'cancelled'"),
    EXPIRED("request_status = 'expired'"),
    APPROVED__COMPLETED("(request_status = 'completed') OR (request_status = 'approved')"),
    PENDING_CANCELLED_EXPIRED("(request_status = 'pending') OR (request_status = 'cancelled') OR (request_status = 'EXPIRED')"),
    BY_USER("user_id = "),
    BY_PERSON("wanted_person_id = ");

    private String condition;
    private int id = -1;

    public String getCondition() {
        if(id != -1) {
            return condition+id;
        }else {
            return condition;
        }
    }

    RequestCondition(String condition) {
        this.condition = condition;
    }

    public RequestCondition setId(int id){
        this.id = id;
        return this;
    }
}

