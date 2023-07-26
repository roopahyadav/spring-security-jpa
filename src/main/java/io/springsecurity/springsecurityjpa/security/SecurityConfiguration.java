package io.springsecurity.springsecurityjpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration   {

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.withUsername("roo").password(encoder.encode("pass")).roles("ADMIN").build();
        UserDetails user = User.withUsername("foo").password(encoder.encode("pass")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/user").hasAnyRole("USER","ADMIN")
                .and().authorizeHttpRequests().requestMatchers("/admin").authenticated()
                .and().authorizeHttpRequests().requestMatchers("/").permitAll()
                .and().formLogin()
                .and().build();
    }
    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

//    @Bean
//    public PasswordEncoder getPasswordEncoded(){
//        return NoOpPasswordEncoder.getInstance();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/user").hasAnyRole("USER","ADMIN")
//                .antMatchers("/").permitAll()
//                 .and().formLogin();
//    }


}
