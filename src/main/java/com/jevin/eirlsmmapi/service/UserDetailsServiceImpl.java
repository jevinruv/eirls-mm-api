package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.User;
import com.jevin.eirlsmmapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username : " + username));

        return UserPrinciple.build(user);
    }
}
