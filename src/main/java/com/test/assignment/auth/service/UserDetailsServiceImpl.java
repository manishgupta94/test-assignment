package com.test.assignment.auth.service;

import com.test.assignment.auth.model.MyUserDetails;
import com.test.assignment.user.domain.model.User;
import com.test.assignment.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Class to load the user by its username from the database.
 *
 * @author Manish Gupta
 * @version $Id: UserDetailsServiceImpl.java 1.0
 * @since 2020-11-06
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the User by its username from the database.
     *
     * @param username Username passed from the login form
     * @return A UserDetails object wrapping the User object.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);

    }
}
