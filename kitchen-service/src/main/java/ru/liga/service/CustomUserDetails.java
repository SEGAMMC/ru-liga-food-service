package ru.liga.service;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.liga.entity.User;

import java.util.stream.Collectors;

public class CustomUserDetails extends User {
//
//    public CustomUserDetails(User user) {
//        super(user.getUsername(), user.getPassword(),
//                user.getRoles().stream()
//                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                        .collect(Collectors.toList()));
//    }
}
