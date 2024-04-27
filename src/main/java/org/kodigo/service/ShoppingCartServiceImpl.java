package org.kodigo.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.kodigo.domain.Product;
import org.kodigo.domain.ShoppingCartItem;
import org.kodigo.repository.IProductRepository;
import org.kodigo.repository.IShoppingCartRepository;

@AllArgsConstructor
public class ShoppingCartServiceImpl implements IShoppingCartService {

    private final IProductRepository productRepository;

    private final IShoppingCartRepository shoppingCartRepository;

    @Override
    public void addProductToCart(Integer userId, Integer productId, Integer quantity) {
        val shoppingCart = shoppingCartRepository.getShoppingCart(userId);

        val product = getProduct(productId);
        productRepository.updateProduct(product);

        if (product.getStock() < quantity) {
            throw new RuntimeException("Product out of stock");
        }

        shoppingCart.getProducts().add(ShoppingCartItem
                .builder()
                .product(product)
                .quantity(quantity)
                .build());

        product.setStock(product.getStock() - quantity);

    }

    @Override
    public void removeProductFromCart(Integer userId, Integer productId) {

        val shoppingCart = shoppingCartRepository.getShoppingCart(userId);

        val stockProduct = getProduct(productId);

        val item = shoppingCart.getProducts().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        stockProduct.setStock(stockProduct.getStock() + item.getQuantity());

        productRepository.updateProduct(stockProduct);

        shoppingCart.getProducts().removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));

    }

    @Override
    public void updateProductQuantity(Integer userId, Integer productId, Integer newQuantity) {

        val shoppingCart = shoppingCartRepository.getShoppingCart(userId);

        val stockProduct = getProduct(productId);

        val item = shoppingCart.getProducts().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        if (stockProduct.getStock() < newQuantity) {
            throw new RuntimeException("Product out of stock");
        }

        productRepository.updateProduct(Product.builder()
                .name(stockProduct.getName())
                .price(stockProduct.getPrice())
                .category(stockProduct.getCategory())
                .stock(stockProduct.getStock() - newQuantity)
                .build());

        item.setQuantity(newQuantity);

    }

    @Override
    public void viewCart(Integer userId) {
        val shoppingCart = shoppingCartRepository.getShoppingCart(userId);

        shoppingCart.getProducts().forEach(cartItem -> {
            val productInfo = getProduct(cartItem.getProduct().getId());
            System.out.println("Product: " + productInfo.getName() + " Quantity: " + cartItem.getQuantity());
        });

    }

    @Override
    public Integer checkout(Integer userId) {

        val shoppingCart = shoppingCartRepository.getShoppingCart(userId);

        val subTotal = shoppingCart.getProducts().stream().reduce(0.00, (acc, cartItem) -> {
            val productInfo = getProduct(cartItem.getProduct().getId());
            return acc + (productInfo.getPrice() * cartItem.getQuantity());
        }, Double::sum);

        shoppingCart.setSubTotal(subTotal);

        val subTotalWithTaxes = shoppingCart.getTaxes().stream()
                .reduce(0.00, (acc, tax) -> acc + (subTotal + tax.getTax()), Double::sum);

        shoppingCart.setSubTotalWithTaxes(subTotalWithTaxes);

        return shoppingCart.getId();

    }

    private Product getProduct(Integer productId) {
        return productRepository.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
