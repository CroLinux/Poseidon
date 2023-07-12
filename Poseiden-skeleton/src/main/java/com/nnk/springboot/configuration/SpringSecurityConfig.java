package com.nnk.springboot.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfiguration {
    @Autowired
    DataSource dataSource;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username , password, 'true' as enabled from users  where username=?")
                .authoritiesByUsernameQuery("select username , role , 'true' as enabled from users  where username=?")
                .passwordEncoder(passwordEncoder());
    }

    public void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests()
                .requestMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**").hasAnyAuthority("USER", "ADMIN", "ROLE_USER")
                .requestMatchers("/user/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(login -> login
                        .defaultSuccessUrl("/bidList/list", true))
                .exceptionHandling(handling -> handling.accessDeniedPage("/app/error"))
                .logout(logout -> logout.logoutUrl("/app-logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login"));
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
    
}

//public class SecurityConfig {
/**	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http.csrf(withDefaults())
		.authorizeHttpRequests(authorize -> authorize
			.anyRequest().authenticated()
		)
		.formLogin(withDefaults())
		.httpBasic(withDefaults());
	return http.build();
	
 	}
*/ 
	
	
	


