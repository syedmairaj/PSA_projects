package com.durgasoft.Config;

import com.durgasoft.Service.JWTService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.csrf().disable().cors().disable();

       //this line of code allow jwt token urls to addfilterbefore method
        //, it simply inserts your jwtFilter into the Spring Security filter
        //chain, which runs for all incoming requests, unless you've specifically configured certain paths to be ignored.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //urls doesnt require token public urls come to this url
        http.authorizeHttpRequests()
          //doesnt require token open to all
                .requestMatchers("/api/vi/app/signup" ,"/api/vi/app/login","/api/vi/property/signup",
                        "/vi/api/images/upload/file/**","/vi/api/rooms/addroom/property/**" ,"/vi/api/rooms/**" )
                .permitAll()
                //api/vi/property/add can only access by property_owner  hasRole can use only one role, hasanyrole can use many roles,
                .requestMatchers("/api/vi/property/addproperty").hasAnyRole("PROPERTYOWNER","ADMIN")
                .requestMatchers("/api/vi/property/delproperty").hasAnyRole("PROPERTYOWNER","ADMIN")
                .requestMatchers("/api/vi/property/Blog/signup").hasRole("ADMIN")
                .anyRequest().authenticated();
        return http.build();

    }
}
