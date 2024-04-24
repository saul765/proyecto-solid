package org.kodigo.service;

public interface IShoppingCartService {
    void addProductToCart(String userId, Integer productId, Integer quantity);

    void removeProductFromCart(String userId, Integer productId);

    void updateProductQuantity(String userId, Integer productId, Integer newQuantity);

    void viewCart(String userId);

    Integer checkout(String userId);

}
