package org.kodigo.repository;

import org.kodigo.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements IUserRepository {

    private final ArrayList<User> users = new ArrayList<>(mockUsers());

    @Override
    public void createUser(User user) {
        users.stream().filter(u -> u.getEmail().equals(user.getEmail()))
                .findAny()
                .ifPresent(u -> {
                    throw new RuntimeException("User already exists");
                });

        users.add(user);

    }

    @Override
    public void updateUser(User user) {
        users.add(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getUserById(Integer userId) {
        return users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.getName().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private List<User> mockUsers() {
        return List.of(
                User.builder()
                        .name("John Doe")
                        .email("doe@yopmail.com")
                        .address("1234 Main St")
                        .password("password")
                        .build(),
                User.builder()
                        .name("Jane Doe")
                        .email("jane@yoomail.com")
                        .address("1234 Main St")
                        .password("password")
                        .build()
        );
    }
}
