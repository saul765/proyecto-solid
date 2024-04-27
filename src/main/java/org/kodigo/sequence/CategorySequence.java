package org.kodigo.sequence;

import static java.util.Objects.isNull;

public class CategorySequence implements BaseSequence {

    private static Integer idCounter = 0;
    private static CategorySequence instance;

    private CategorySequence() {
    }

    public static CategorySequence getInstance() {
        if (isNull(instance)) {
            instance = new CategorySequence();
        }
        return instance;
    }

    @Override
    public Integer getNextId() {
        return ++idCounter;
    }
}
