package org.kodigo.sequence;

import static java.util.Objects.isNull;

public class ProductSequence implements BaseSequence {


    private static Integer idCounter = 0;
    private static ProductSequence instance;

    private ProductSequence() {
    }

    public static ProductSequence getInstance() {
        if (isNull(instance)) {
            instance = new ProductSequence();
        }
        return instance;
    }

    @Override
    public Integer getNextId() {
        return ++idCounter;
    }
}
