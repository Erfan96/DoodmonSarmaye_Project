package com.example.DoodmonSarmayeProject.service;

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

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public UserDetails loadUserByPhoneNumber(String phone) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByPhoneNumber(phone);
        return user.orElse(null);
    }

   public UserDetails loadUserByNationalCode(String code) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByNationalCode(code);
        return user.orElse(null);
    }

    public List<User> findAll() {
        return this.userRepository.findAllByOrderByLastNameAsc();
    }

    public User loadUserByID(Long id)  {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new UsernameNotFoundException("");
        }
    }

    public void saveUser(FrontUser frontUser) {
        User user = loadUserByID(frontUser.getId());

        user.setUsername(frontUser.getUserName());
        user.setPassword(passwordEncoder.encode(frontUser.getPassword()));
        user.setFirstName(frontUser.getFirstName());
        user.setLastName(frontUser.getLastName());
        user.setNationalCode(frontUser.getNationalCode());
        user.setPhoneNumber(frontUser.getPhoneNumber());
        user.setEmail(frontUser.getEmail());

        this.userRepository.save(user);
    }
}
