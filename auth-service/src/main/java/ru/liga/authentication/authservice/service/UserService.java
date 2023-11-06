package ru.liga.authentication.authservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import ru.liga.authentication.authservice.dto.RegDto;
import ru.liga.authentication.authservice.model.CustomUserDetails;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserDetailsService userDetailsService;

    @Qualifier("defaultPasswordEncoder")
    private final PasswordEncoder defaultPasswordEncoder;

    public UserService(UserDetailsService userDetailsService, PasswordEncoder defaultPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
    }
    //Регистрация user
    public ResponseEntity<String> createUser(RegDto request) {
        CustomUserDetails customUserDetails = new CustomUserDetails(
                request.getUsername(), defaultPasswordEncoder.encode(request.getPassword()),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        try {
            if (userDetailsService.loadUserByUsername(request.getUsername()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким именем уже существует");
            }

        } catch (UsernameNotFoundException e) {
            ((JdbcUserDetailsManager) userDetailsService).createUser(customUserDetails);
            return ResponseEntity.ok("Пользователь успешно создан");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
    }

//
//	//Регистрация заказчика
//    public ResponseEntity<String> createCustomer(RegDto request) {
//        CustomUserDetails customUserDetails = new CustomUserDetails(
//                request.getUsername(), defaultPasswordEncoder.encode(request.getPassword()),
////                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//				List.of(new SimpleGrantedAuthority("ROLE_USER")
//					,new SimpleGrantedAuthority("ROLE_CUSTOMER")));
//
//        try {
//            if (userDetailsService.loadUserByUsername(request.getUsername()) != null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким именем уже существует");
//            }
//
//        } catch (UsernameNotFoundException e) {
//            ((JdbcUserDetailsManager) userDetailsService).createUser(customUserDetails);
//            return ResponseEntity.ok("Пользователь успешно создан");
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
//    }
//
//
//
//	//Регистрация курьера
//	    public ResponseEntity<String> createCourier(RegDto request) {
//        CustomUserDetails customUserDetails = new CustomUserDetails(
//                request.getUsername(), defaultPasswordEncoder.encode(request.getPassword()),
////                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//				List.of(new SimpleGrantedAuthority("ROLE_USER")
//					,new SimpleGrantedAuthority("ROLE_COURIER")));
//
//        try {
//            if (userDetailsService.loadUserByUsername(request.getUsername()) != null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким именем уже существует");
//            }
//
//        } catch (UsernameNotFoundException e) {
//            ((JdbcUserDetailsManager) userDetailsService).createUser(customUserDetails);
//            return ResponseEntity.ok("Пользователь успешно создан");
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
//    }
//
//
//		//Регистрация Ресторана
//	    public ResponseEntity<String> createRestaurant(RegDto request) {
//
//        CustomUserDetails customUserDetails = new CustomUserDetails(
//                request.getUsername(), defaultPasswordEncoder.encode(request.getPassword()),
////                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//				List.of(new SimpleGrantedAuthority("ROLE_USER")
//					,new SimpleGrantedAuthority("ROLE_RESTAURANT")));
//
//        try {
//            if (userDetailsService.loadUserByUsername(request.getUsername()) != null) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким именем уже существует");
//            }
//
//        } catch (UsernameNotFoundException e) {
//            ((JdbcUserDetailsManager) userDetailsService).createUser(customUserDetails);
//            return ResponseEntity.ok("Пользователь успешно создан");
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
//    }
}

