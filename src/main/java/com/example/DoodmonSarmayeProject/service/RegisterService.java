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
public class RegisterService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Transactional
    public UserDetails loadUserByPhoneNumber(String phone) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByPhoneNumber(phone);
        return user.orElse(null);
    }

    @Transactional
    public UserDetails loadUserByNationalCode(String code) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByNationalCode(code);
        return user.orElse(null);
    }

    @Transactional
    public void saveUser(FrontUser fUser) {
        User user = new User();

        user.setUsername(fUser.getUserName());
        user.setPassword(passwordEncoder.encode(fUser.getPassword()));
        user.setFirstName(fUser.getFirstName());
        user.setLastName(fUser.getLastName());
        user.setNationalCode(fUser.getNationalCode());
        user.setPhoneNumber(fUser.getPhoneNumber());
        user.setEmail(fUser.getEmail());
        user.setEnabled(true);

        user.grantedAuthority(Role.ROLE_INVESTOR);

        this.userRepository.save(user);
    }
}
