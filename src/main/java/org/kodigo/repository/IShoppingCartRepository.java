package org.kodigo.repository;

import org.kodigo.domain.Product;
import org.kodigo.domain.ShoppingCart;

public interface IShoppingCartRepository {

    ShoppingCart getShoppingCart(Integer userId);

    void addProductToCart(Integer userId, Product product, Integer quantity);

    void removeProductFromCart(Integer userId, Integer productId);

}
