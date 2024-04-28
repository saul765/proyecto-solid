package org.kodigo;

import lombok.val;
import org.kodigo.component.*;
import org.kodigo.domain.Category;
import org.kodigo.domain.Product;
import org.kodigo.domain.User;
import org.kodigo.enums.TableType;
import org.kodigo.repository.*;
import org.kodigo.service.*;

import java.util.List;

public class Main {


    IProductRepository productRepository;

    ICategoryRepository categoryRepository;

    IShoppingCartRepository shoppingCartRepository;

    IUserRepository userRepository;


    IShoppingCartService shoppingCartService;

    IProductService productService;

    IUserService userService;

    ICategoryService categoryService;


    public static void main(String[] args) {
        Main main = new Main();
        main.execute();
    }

    private void execute() {
        repositoriesInitialization();
        servicesInitialization();
        bindMockCategories();
        // listing all users
        listingUsers();
        // creating a new user
        createUser();
        // listing all categories before creating a new product
        listingCategories();
        // creating a new category
        createCategory();
        // updating a category typo
        updateCategory();
        // listing all products
        listingProducts();
        // creating a new product
        createProduct();
        // listing eduardo shopping cart
        listingShoppingCartByUser(3);
        // adding a product to eduardo's shopping cart
        addProductToCart(3);

    }

    private void addProductToCart(Integer userId) {
        val productsToAdd = productService.getProducts()
                .stream().limit(3)
                .toList();

        productsToAdd.forEach(product -> shoppingCartService.addProductToCart(userId, product.getId(), 1));
        System.out.println("Listing shopping cart after adding products");
        listingShoppingCartByUser(userId);
    }

    private void createProduct() {

        val category = categoryService.getCategoryByName("Beauty");
        val product = Product.builder()
                .name("Lipstick")
                .price(25.0)
                .category(category)
                .description("Red lipstick")
                .stock(10)
                .build();

        productService.createProduct(product);
        // listing all products after creating a new product
        System.out.println("Listing all products after creating a new product");
        listingProducts();
    }

    private void updateCategory() {

        val category = categoryService.getCategoryById(5);
        category.setDescription("Beauty products");
        categoryService.updateCategory(category);
        // listing all categories after updating a category
        System.out.println("Listing all categories after updating beauty category");
        listingCategories();
    }

    private void createCategory() {
        val category = Category.builder()
                .name("Beauty")
                .description("Beaut products")
                .build();
        categoryService.createCategory(category);
        // listing all categories after creating a new category
        listingCategories();
    }


    private void listingCategories() {
        System.out.println("Listing all categories");
        CategoryTableVisualizationComponent visual = (CategoryTableVisualizationComponent) TableVisualizationFactory.getTableVisualization(TableType.CATEGORY);
        visual.showData(categoryRepository.getAll());
    }

    private void listingUsers() {
        System.out.println("Listing all users");
        UsersTableVisualizationComponent visual = (UsersTableVisualizationComponent) TableVisualizationFactory.getTableVisualization(TableType.USER);
        visual.showData(userService.getAll());
    }

    private void createUser() {
        User user = User.builder()
                .name("Eduardo Martinez")
                .email("eduardo@yopmail.com")
                .password("123")
                .address("Test Address")
                .build();

        userService.createUser(user);

        // listing all users after creating a new user
        System.out.println("Listing all users after creating a new user");
        listingUsers();

    }

    private void listingProducts() {
        System.out.println("Listing all products");
        ProductsTableVisualizationComponent visual = (ProductsTableVisualizationComponent) TableVisualizationFactory.getTableVisualization(TableType.PRODUCT);
        visual.showData(productService.getProducts());
    }

    private void listingShoppingCartByUser(Integer userId) {
        System.out.println("Listing shopping cart of user with id " + userId);
        ShoppingCartVisualizationComponent visual = (ShoppingCartVisualizationComponent) TableVisualizationFactory.getTableVisualization(TableType.SHOPPING_CART);
        visual.showData(List.of(shoppingCartService.getCart(userId)));
    }

    private void repositoriesInitialization() {
        productRepository = new ProductRepositoryImpl();
        categoryRepository = new CategoryRepositoryImpl();
        shoppingCartRepository = new ShoppingCartRepositoryImpl();
        userRepository = new UserRepositoryImpl();
    }

    private void servicesInitialization() {
        shoppingCartService = new ShoppingCartServiceImpl(productRepository, shoppingCartRepository);
        productService = new ProductServiceImpl(productRepository, categoryRepository);
        userService = new UserServiceImpl(userRepository);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    private void bindMockCategories() {
        if (productService instanceof ProductServiceImpl) {
            ((ProductServiceImpl) productService).bindMockCategories();
        } else {
            throw new RuntimeException("Cannot cast productService to ProductServiceImpl");
        }
    }


}