package com.elaamiri.patientsmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override // spécifier les droits d'accès
    protected void configure(HttpSecurity http) throws Exception {
        // hey Spring, je veux utiliser un 'form' d'authetification
        http.formLogin(); //  default login form
        //http.formLogin().loginPage("/login"); // my own login form
        // droits d'acces
        http.authorizeRequests().antMatchers("/", "/home/**").permitAll(); // for all
        http.authorizeRequests().antMatchers("/editPatient/**"
                , "/deletePatient/**", "/saveEditedPatient/**"
                , "/saveNewPatient/**", "/addNewPatient/**")
                .hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/patients/**").hasRole("USER");
        // toutes les requets nécissite une authentification
        http.authorizeHttpRequests().anyRequest().authenticated();
        // configure errors (/error403 is a vue)
        http.exceptionHandling().accessDeniedPage("/error403");

    }

    @Override // Spécifier la stratégie avec laquelle Spring Sec va
                // chercher les utilisateurs authorisés
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = getPasswordEncoder();
        // inMemory authentication = les utilisateurs à utiliser l'application
        // seront stockés dans la mémoire (utiles pour les testes)
            auth.inMemoryAuthentication()
                    .withUser("user")
                    .password(passwordEncoder.encode("user"))
                    .roles("USER")
                    .and()
                    .withUser("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles("USER","ADMIN");
            //auth.inMemoryAuthentication() ... on peut reutiliser ...
            /*auth.inMemoryAuthentication()
                    .withUser("user1")
                    .password("{noop}1234") // // noop = no encryption needed
                    .roles("USER");*/


    }

    @Bean
        // executé au démarrage, et place
        // l'objet retourné dans le context
        // comme Spring Bean (il peut etre injecté n'import où)
    PasswordEncoder getPasswordEncoder(){
        // retourner le type d'encodage
        // get spring version
        //System.out.println(SpringVersion.getVersion()); // 5.3.16
        return  new BCryptPasswordEncoder();
    }
}
