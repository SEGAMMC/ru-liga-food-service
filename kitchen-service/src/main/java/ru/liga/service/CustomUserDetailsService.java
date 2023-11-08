package ru.liga.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.liga.entity.User;
import ru.liga.repository.hibernate.CustomerRepositoryAuth;

@Service
public class CustomUserDetailsService
//        implements UserDetailsService
{
//
//    @Lazy
//    @Autowired
//    private CustomerRepositoryAuth customerRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = customerRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return (UserDetails) new CustomUserDetails(user);
//    }
}