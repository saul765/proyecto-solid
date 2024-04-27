package org.kodigo.repository;

import org.kodigo.domain.Product;
import org.kodigo.domain.ShoppingCart;
import org.kodigo.domain.ShoppingCartItem;

import java.util.ArrayList;
import java.util.Collections;

public class ShoppingCartRepositoryImpl implements IShoppingCartRepository {

    private final ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();

    @Override
    public ShoppingCart getShoppingCart(Integer userId) {
        return shoppingCarts.stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElseGet(() -> {
                    ShoppingCart newCart = ShoppingCart.builder()
                            .userId(userId)
                            .products(new ArrayList<>())
                            .taxes(Collections.emptyList())
                            .build();
                    shoppingCarts.add(newCart);
                    return newCart;
                });
    }

    @Override
    public void addProductToCart(Integer userId, Product product, Integer quantity) {
        ShoppingCart shoppingCart = getShoppingCart(userId);

        shoppingCart.getProducts().add(ShoppingCartItem.builder()
                .product(product)
                .quantity(quantity)
                .build());

    }

    @Override
    public void removeProductFromCart(Integer userId, Integer productId) {

        shoppingCarts.stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .ifPresent(cart -> {
                    cart.getProducts()
                            .removeIf(product -> product.getProduct()
                                    .getId()
                                    .equals(productId));
                });

    }




}
