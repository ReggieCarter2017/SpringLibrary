package org.example.security;

import lombok.AllArgsConstructor;
import org.example.models.User;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        Optional<User> user = userRepository.getUserByUsername(username);
        UserDetailsImp userDetailsImp =  user.map(UserDetailsImp::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.
                User(userDetailsImp.getUsername(), customPasswordEncoder.encode(userDetailsImp.getPassword()), userDetailsImp.getAuthorities());
    }
}


