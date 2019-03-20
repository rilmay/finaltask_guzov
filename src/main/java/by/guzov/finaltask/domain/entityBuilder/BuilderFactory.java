package by.guzov.finaltask.domain.entityBuilder;

import by.guzov.finaltask.domain.Record;
import by.guzov.finaltask.domain.Request;
import by.guzov.finaltask.domain.User;
import by.guzov.finaltask.domain.WantedPerson;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuilderFactory {
    private static BuilderFactory INSTANCE;
    private static Lock lock = new ReentrantLock();
    private final Builder<User> userBuilder = new UserBuilder();
    private final Builder<Request> requestBuilder = new RequestBuilder();
    private final Builder<WantedPerson> wantedPersonBuilder = new WantedPersonBuilder();
    private final Builder<Record> recordBuilder = new RecordBuilder();

    public static BuilderFactory getInstance() {

        lock.lock();
        try {
            if (INSTANCE == null) {
                INSTANCE = new BuilderFactory();
            }

        } finally {
            lock.unlock();
        }
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

    public Builder<Record> getRecordBuilder() {
        return recordBuilder;
    }

    private BuilderFactory() {

    }


}
