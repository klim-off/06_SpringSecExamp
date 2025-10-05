package com.klimov.SpringSecExamp.service;

import com.klimov.SpringSecExamp.model.UserPrincipal;
import com.klimov.SpringSecExamp.model.Users;
import com.klimov.SpringSecExamp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if (user == null){
            System.out.println("User not found: "+ username);
            throw new UsernameNotFoundException("User "+ username + " not found");
        }

        return new UserPrincipal(user);
    }
}
