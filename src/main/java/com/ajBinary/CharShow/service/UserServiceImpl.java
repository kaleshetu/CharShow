package com.ajBinary.CharShow.service;

import com.ajBinary.CharShow.entity.User;
import com.ajBinary.CharShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> userOpt= userRepository.findByUsername(username);
        UserBuilder builder = null;
        if(userOpt.isPresent()){
            User currentUser =userOpt.get();
            builder = org.springframework.security.core.userdetails
                    .User.withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());
            return builder.build();
        }else{
            throw new UsernameNotFoundException("user not found");
        }
        //return null;
    }
}
