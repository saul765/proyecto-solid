package org.kodigo.service;

public interface IShoppingCartService {
    void addProductToCart(Integer userId, Integer productId, Integer quantity);

    void removeProductFromCart(Integer userId, Integer productId);

    void updateProductQuantity(Integer userId, Integer productId, Integer newQuantity);

    void viewCart(Integer userId);

    Integer checkout(Integer userId);

}
