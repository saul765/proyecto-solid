package org.kodigo.service;

import org.kodigo.domain.ShoppingCart;

public interface IShoppingCartService {

    void addProductToCart(Integer userId, Integer productId, Integer quantity);

    void removeProductFromCart(Integer userId, Integer productId);

    void updateProductQuantity(Integer userId, Integer productId, Integer newQuantity);

    ShoppingCart getCart(Integer userId);

    Integer checkout(Integer userId);

}
