package com.minh.Backend.service.impl;

import com.minh.Backend.dto.user.UserCreateDTO;
import com.minh.Backend.entity.Cart;
import com.minh.Backend.entity.Order;
import com.minh.Backend.entity.User;
import com.minh.Backend.entity.enums.Role;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.repository.UserRepository;
import com.minh.Backend.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired private UserRepository userRepository;
    @Autowired private CartService cartService;
    @Autowired private ModelMapper modelMapper;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find user id: " + id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    public User createUser(User user) {
        save(user);
        cartService.createCart(0.0, 0.0, 0.1, user);
        return user;
    }

    public User createUser(UserCreateDTO userCreateDTO) {
        Cart cart = new Cart(0.0,0.0,0.1);
        List<Order> orders = new ArrayList<>();
        String hashPwd = passwordEncoder.encode(userCreateDTO.getPassword());
        User user = new User(
                userCreateDTO.getUsername(),
                hashPwd,
                cart,
                orders
        );
        if (userCreateDTO.getRole().equalsIgnoreCase("USER")) user.setRole(Role.USER);
        else if (userCreateDTO.getRole().equalsIgnoreCase("ADMIN")) user.setRole(Role.ADMIN);
        return save(user);
    }

    public User updateInfo(Integer id, User user) {
        User upUser = findById(id);
        upUser.setName(user.getName());
        upUser.setEmail(user.getEmail());
        upUser.setAddress(user.getAddress());
        upUser.setTel(user.getTel());
        upUser.setBirthDate(user.getBirthDate());
        upUser.setAvatar(user.getAvatar());
        return save(upUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User curUser = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(curUser.getUsername(),
                curUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(curUser.getRole().getUserRole()))
        );
    }
}
