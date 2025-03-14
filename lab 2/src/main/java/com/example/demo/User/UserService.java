package com.example.demo.User;

import com.example.demo.User.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(CreateUserDto userDto) {
        User user = new User(
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getPhoneNumber()
        );
        return this.userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(String.valueOf(id));
    }

    public User updateUser(Integer id, CreateUserDto userDto) {
        return userRepository.findById(String.valueOf(id)).map(user -> {
            user.setEmail(userDto.getEmail());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setPhoneNumber(userDto.getPhoneNumber());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(String.valueOf(id))) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(String.valueOf(id));
    }
}
