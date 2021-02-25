//package com.example.DoodmonSarmayeProject;
//
//import com.example.DoodmonSarmayeProject.entities.Role;
//import com.example.DoodmonSarmayeProject.entities.User;
//import com.example.DoodmonSarmayeProject.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyCommandLineRunner implements CommandLineRunner {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setFirstName("Ali");
//        user.setLastName("Karim");
//        user.setUsername("admin");
//        user.setEmail("ali@yahoo.com");
//        user.setNationalCode("111");
//        user.setPhoneNumber("09121115544");
//        user.setPassword(passwordEncoder.encode("1234"));
//        user.grantedAuthority(Role.ROLE_ADMIN);
//        userRepository.save(user);
//    }
//}
