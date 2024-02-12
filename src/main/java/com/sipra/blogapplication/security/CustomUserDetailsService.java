package com.sipra.blogapplication.security;

import com.sipra.blogapplication.exceptions.ResourceNotFoundException;
import com.sipra.blogapplication.models.User;
import com.sipra.blogapplication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","User Email",username));
        UserDetails userDetails = new CustomUserDetails(user);
        return userDetails;
    }
}
