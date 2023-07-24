package io.springsecurity.springsecurityjpa.model;

import io.springsecurity.springsecurityjpa.model.MyUserDetails;
import io.springsecurity.springsecurityjpa.model.User;
import io.springsecurity.springsecurityjpa.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
       user.orElseThrow(()-> new UsernameNotFoundException("Not found" + username));
        return user.map(MyUserDetails::new).get();
    }
}
