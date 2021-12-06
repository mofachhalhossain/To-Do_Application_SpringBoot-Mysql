package com.example.springProject.springProject.config;

import com.example.springProject.springProject.Dao.UserRepository;
import com.example.springProject.springProject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*In this class UserDetailsService interface fetched*/
public class userDetailsServiceImplement implements UserDetailsService {
    /*fetching jpaRepository*/
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*calling userRepository*/
        User user = userRepository.getUserByUserName(username);

        /*check user is available or not*/
        if (user == null) {
            throw new UsernameNotFoundException("Could not found user");
        }

        /*calling custom user detail via jpaRepository*/
        CustomUserDetail customUserDetail = new CustomUserDetail(user);

        return customUserDetail;
    }
}
