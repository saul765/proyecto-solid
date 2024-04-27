package org.kodigo.sequence;

import static java.util.Objects.isNull;

public class UserSequence implements BaseSequence {

    private static Integer idCounter = 0;
    private static UserSequence instance;

    private UserSequence() {
    }

    public static UserSequence getInstance() {
        if (isNull(instance)) {
            instance = new UserSequence();
        }
        return instance;
    }

    @Override
    public Integer getNextId() {
        return ++idCounter;
    }
}
