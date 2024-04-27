package org.kodigo.sequence;

public class ShoppingCartSequence {

    private static Integer idCounter = 0;
    private static ShoppingCartSequence instance;

    private ShoppingCartSequence() {
    }

    public static ShoppingCartSequence getInstance() {
        if (instance == null) {
            instance = new ShoppingCartSequence();
        }
        return instance;
    }

    public Integer getNextId() {
        return ++idCounter;
    }
}
