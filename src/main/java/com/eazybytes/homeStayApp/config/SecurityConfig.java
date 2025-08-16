package com.eazybytes.homeStayApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean

    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


        http.csrf((csrf)->csrf.ignoringRequestMatchers("/saveMsg")
                .ignoringRequestMatchers("/public/**")
                .ignoringRequestMatchers("/api/**")
                .ignoringRequestMatchers("/project/actuator/**")
                .ignoringRequestMatchers("/data-api/**")).authorizeHttpRequests((requests) -> {
                     requests
                     .requestMatchers("/dashboard").authenticated()
                             .requestMatchers("/displayProfile").authenticated()
                             .requestMatchers("/updateProfile").authenticated()
                             .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                             .requestMatchers("/project/actuator/**").hasRole("ADMIN")
                             .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                             .requestMatchers("/admin/**").hasRole("ADMIN")
                             .requestMatchers("/api/**").authenticated()
                             .requestMatchers("/student/**").hasRole("GUEST")
                             .requestMatchers("/public/**").permitAll()
                             .requestMatchers("/booking/**").authenticated()
                             .requestMatchers("/data-api/**").authenticated()
                    .requestMatchers("/home","/").permitAll()//booking
                    .requestMatchers("/holidays/**").permitAll()
                    .requestMatchers("/contact").permitAll()

                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/saveMsg").permitAll()
                    .requestMatchers("/courses").permitAll()
                     //.requestMatchers("/treks").permitAll()
                    .requestMatchers("/about").permitAll()
                             .requestMatchers("/treks").permitAll()
                    .requestMatchers("/assets/**").permitAll()
                    .requestMatchers("/logout").permitAll()
                    .requestMatchers("/login").permitAll();
        });
        http.formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                                         .defaultSuccessUrl("/dashboard")
                                         .failureUrl("/login?error=true")
                                         .permitAll())
            .logout(logOutConfiguerer -> logOutConfiguerer.logoutSuccessUrl("/login?logout=true")
                                         .invalidateHttpSession(true)
                                         .permitAll());
        http.httpBasic(Customizer.withDefaults());

        return (SecurityFilterChain)http.build();
    }
@Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
}

}
