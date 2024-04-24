package org.kodigo.repository;

public interface IShoppingCartRepository {

    void addProductToCart(Integer productId, Integer quantity);

    void removeProductFromCart(Integer productId);

    void updateProductQuantity(Integer productId, Integer quantity);

    void viewCart();

    void checkout();
}
