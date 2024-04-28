package org.kodigo.service;

import org.kodigo.domain.User;

import java.util.List;

public interface IUserService {

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Integer userId);

    List<User> getAll();

    User getUserById(Integer userId);

    User getUserByUsername(String username);

    User getUserByEmail(String email);
}
