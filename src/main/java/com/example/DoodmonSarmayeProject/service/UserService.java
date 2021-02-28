package com.example.DoodmonSarmayeProject.service;

import com.example.DoodmonSarmayeProject.entities.Role;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.repository.UserRepository;
import com.example.DoodmonSarmayeProject.user.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isPresent()) {
           return user.get();
        }
        else {
          throw new UsernameNotFoundException(String.format("Username[%s] not found!!!"));
        }

    }
}
