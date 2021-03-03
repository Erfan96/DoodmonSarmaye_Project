package com.example.DoodmonSarmayeProject.configure;

import com.example.DoodmonSarmayeProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

        String username = authentication.getName();

        System.out.println("username " + username);

        UserDetails theUser = userService.loadUserByUsername(username);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("log-user", theUser);

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
    }
}
