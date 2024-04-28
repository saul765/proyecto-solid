package org.kodigo.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.kodigo.domain.User;
import org.kodigo.repository.IUserRepository;

import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public void createUser(User user) {
        val users = userRepository.getAll();
        users.stream().filter(u -> u.getEmail().equals(user.getEmail()))
                .findAny()
                .ifPresent(u -> {
                    throw new RuntimeException("User already exists");
                });

        userRepository.createUser(user);

    }

    @Override
    public void updateUser(User user) {

        val users = userRepository.getAll();
        User searchUser = users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isRemoved = users.remove(searchUser);

        if (!isRemoved) {
            throw new IllegalArgumentException("User not found");
        } else {
            userRepository.updateUser(user);
        }
    }

    @Override
    public void deleteUser(Integer userId) {

        val users = userRepository.getAll();

        val user = users.stream().filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.deleteUser(user.getId());
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
