package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nnk.springboot.repositories.UserRepository;

import com.nnk.springboot.domain.User;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/home", "/css/*", "/img/*", "/login", "/error")
                        .permitAll()
                        .requestMatchers("/user/list")
                        .hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/bidList/list")
                        .permitAll())
                .logout(logout -> logout
                        .invalidateHttpSession(true).clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                        .logoutSuccessUrl("/login")
                        .permitAll());
        http.authenticationProvider(authentication());       
        return http.build();
    }
	/**
 	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.csrf(withDefaults())
                 .authorizeHttpRequests((requests) -> requests
                                 .requestMatchers("/login").permitAll()
                                 .requestMatchers("/").permitAll()
                                 .requestMatchers("/css/**").permitAll()
                                 .requestMatchers("/home").permitAll()
                                 .requestMatchers("/error").permitAll()
                                 .requestMatchers("/user/list").hasAuthority("ADMIN")
                                 .anyRequest().authenticated()
                 )
                 .formLogin((form) -> form
                		    .loginProcessingUrl("/login")
                                 .defaultSuccessUrl("/bidList/list")
                                 .permitAll()
                 )
                 .logout((logout) -> logout

                         .invalidateHttpSession(true).clearAuthentication(true)
                		    .deleteCookies("JSESSIONID", "remember-me") // Optionally, specify additional cookies you want to delete
                		    .deleteCookies()
                		    .clearAuthentication(true)
                		    .invalidateHttpSession(true)
                		    .logoutSuccessUrl("/login")
                		    .permitAll())
                 .exceptionHandling(handling -> handling.accessDeniedPage("/access-denied"));
         return http.build();
     }
*/
    @Bean
    public DaoAuthenticationProvider authentication() {
        DaoAuthenticationProvider authentication = new DaoAuthenticationProvider();
        UserDetailsService userDetailsService = new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findUserByUsername(username);
                GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
                UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));

                return details;
            }
        };
        authentication.setUserDetailsService(userDetailsService);
        authentication.setPasswordEncoder(passwordEncoder());
        return authentication;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}