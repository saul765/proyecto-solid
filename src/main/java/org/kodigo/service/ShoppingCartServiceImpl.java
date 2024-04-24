package org.kodigo.service;

import lombok.val;
import org.kodigo.domain.PlatformService;
import org.kodigo.domain.Product;
import org.kodigo.domain.ShoppingCart;
import org.kodigo.domain.ShoppingCartItem;
import org.kodigo.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceImpl implements IShoppingCartService {

    private final IProductRepository productRepository;

    private final ArrayList<ShoppingCart> shoppingCarts;

    public ShoppingCartServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
        this.shoppingCarts = new ArrayList<>();
    }


    @Override
    public void addProductToCart(String userId, Integer productId, Integer quantity) {
        val shoppingCart = getShoppingCart(userId);

        val product = getProduct(productId);

        if (product.getStock() < quantity) {
            throw new RuntimeException("Product out of stock");
        }

        shoppingCart.getProducts().add(ShoppingCartItem.builder()
                .productId(productId)
                .quantity(quantity)
                .build());

        productRepository.updateProduct(productId, Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .stock(product.getStock() - quantity)
                .build());

    }

    @Override
    public void removeProductFromCart(String userId, Integer productId) {

        val shoppingCart = getShoppingCart(userId);

        val stockProduct = getProduct(productId);

        val item = shoppingCart.getProducts().stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));


        productRepository.updateProduct(productId, Product.builder()
                .name(stockProduct.getName())
                .price(stockProduct.getPrice())
                .category(stockProduct.getCategory())
                .stock(stockProduct.getStock() + item.getQuantity())
                .build());

        shoppingCart.getProducts().removeIf(product -> product.getProductId().equals(productId));

    }

    @Override
    public void updateProductQuantity(String userId, Integer productId, Integer newQuantity) {

        val shoppingCart = getShoppingCart(userId);

        val stockProduct = getProduct(productId);

        val item = shoppingCart.getProducts().stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        if (stockProduct.getStock() < newQuantity) {
            throw new RuntimeException("Product out of stock");
        }

        productRepository.updateProduct(productId, Product.builder()
                .name(stockProduct.getName())
                .price(stockProduct.getPrice())
                .category(stockProduct.getCategory())
                .stock(stockProduct.getStock() - newQuantity)
                .build());

        item.setQuantity(newQuantity);

    }

    @Override
    public void viewCart(String userId) {
        val shoppingCart = getShoppingCart(userId);

        shoppingCart.getProducts().forEach(product -> {
            val productInfo = getProduct(product.getProductId());
            System.out.println("Product: " + productInfo.getName() + " Quantity: " + product.getQuantity());
        });

    }

    @Override
    public Integer checkout(String userId) {

        val shoppingCart = getShoppingCart(userId);

        val subTotal = shoppingCart.getProducts().stream().reduce(0.00, (acc, cartItem) -> {
            val productInfo = getProduct(cartItem.getProductId());
            return acc + (productInfo.getPrice() * cartItem.getQuantity());
        }, Double::sum);

        shoppingCart.setSubTotal(subTotal);

        val subTotalWithTaxes = shoppingCart.getTaxes().stream()
                .reduce(0.00, (acc, tax) -> acc + (subTotal + tax.getTax()), Double::sum);

        shoppingCart.setSubTotalWithTaxes(subTotalWithTaxes);

        return shoppingCart.getId();

    }

    private ShoppingCart getShoppingCart(String userId) {
        return shoppingCarts.stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElseGet(() -> {
                    ShoppingCart newCart = ShoppingCart.builder()
                            .userId(userId)
                            .products(new ArrayList<>())
                            .taxes(List.of(PlatformService
                                    .builder()
                                    .build()))
                            .build();
                    shoppingCarts.add(newCart);
                    return newCart;
                });
    }

    private Product getProduct(Integer productId) {
        return productRepository.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
