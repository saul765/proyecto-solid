package org.kodigo.repository;

import org.kodigo.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements IUserRepository {

    private ArrayList<User> users = new ArrayList<>(mockUsers());

    @Override
    public void createUser(User user) {


    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User getUserById(Integer userId) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
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
