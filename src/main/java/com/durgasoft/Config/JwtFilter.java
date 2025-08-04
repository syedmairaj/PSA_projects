package com.durgasoft.Config;

import com.durgasoft.Entity.Signup;
import com.durgasoft.Repository.SignupRepository;
import com.durgasoft.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private SignupRepository signupRepository;

    public JwtFilter(JWTService jwtService, SignupRepository signupRepository) {
        this.jwtService = jwtService;
        this.signupRepository = signupRepository;
    }

    @Override   //this method auto when create Onceperrequest it will asked you to implement
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization"); // by using this token we need to extract the username decrypt the token and verify the token
      // this if condition check if the token is not there then it will go to filterChain.doFilter(request,response) and then this will redirect to SecurityConfig.java
        // permitall()
//
       if (token != null && token.startsWith("Bearer ")) {

          String token1 = token.substring(8, token.length() - 1);
           String username = jwtService.getUsername(token1);
           Optional<Signup> user = signupRepository.findByUsername(username);
           if (user.isPresent()) {
               Signup getuserdetails = user.get();
               //use inbuild method "Collections.sigleton (new SimpleGrantedAuthority)" to authorize roles to access urls and sigleton use only one String value
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(getuserdetails, null, Collections.singleton(new SimpleGrantedAuthority(getuserdetails.getRole())));
               authenticationToken.setDetails(new WebAuthenticationDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
           System.out.println(username);

       } // if open  urls come it will block hence we are using filterchain to redirect to securityconfig to use permitlAll
        // , if if condition is false then jvm will come to filterchain and filterchain redirects to securityconfig
        filterChain.doFilter(request,response);

    }
}