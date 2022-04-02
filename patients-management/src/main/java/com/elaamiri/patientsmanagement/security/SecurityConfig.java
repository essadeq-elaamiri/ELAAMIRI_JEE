package com.elaamiri.patientsmanagement.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override // spécifier les droits d'accès
    protected void configure(HttpSecurity http) throws Exception {
        // hey Spring, je veux utiliser un 'form' d'authetification
        http.formLogin(); //  default login form
        //http.formLogin().loginPage("/login"); // my own login form
        // droits d'acces
        // toutes les requets nécissite une authentification
        http.authorizeHttpRequests().anyRequest().authenticated();

    }

    @Override // Spécifier la stratégie avec laquelle Spring Sec va
                // chercher les utilisateurs authorisés
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // inMemory authentication = les utilisateurs à utiliser l'application
        // seront stockés dans la mémoire (utiles pour les testes)
            auth.inMemoryAuthentication()
                    .withUser("user1")
                    .password("{noop}0000user") // noop = no encryption needed
                    .roles("USER")
                    .and()
                    .withUser("admin")
                    .password("admin0000")
                    .roles("USER","ADMIN");
            //auth.inMemoryAuthentication() ... on peut reutiliser ...

    }
}
