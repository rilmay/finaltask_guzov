package by.guzov.finaltask.domain.Builder;

import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;

public class BuilderFactory {
    private static final BuilderFactory INSTANCE = new BuilderFactory();
    private final Builder<User> userBuilder = new UserBuilder();
    private final Builder<Request> requestBuilder = new RequestBuilder();
    private final Builder<WantedPerson> wantedPersonBuilder = new WantedPersonBuilder();

    public static BuilderFactory getInstance() {
        return INSTANCE;
    }

    public Builder<User> getUserBuilder() {
        return userBuilder;
    }

    public Builder<Request> getRequestBuilder() {
        return requestBuilder;
    }

    public Builder<WantedPerson> getWantedPersonBuilder() {
        return wantedPersonBuilder;
    }

    private BuilderFactory() {

    }
}