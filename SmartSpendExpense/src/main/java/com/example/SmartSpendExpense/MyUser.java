package com.example.SmartSpendExpense;

import com.example.SmartSpendExpense.model.Users;
import com.example.SmartSpendExpense.repositery.UserRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUser implements UserDetailsService {
    @Autowired
    UserRepositery userRepositery;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userRepositery.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UsePrincipal(user);
    }
}
