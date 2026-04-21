package com.backend.TaskManager.service;


import com.backend.TaskManager.dto.AuthRequest;
import com.backend.TaskManager.dto.AuthResponse;
import com.backend.TaskManager.entity.User;
import com.backend.TaskManager.enums.Role;
import com.backend.TaskManager.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;


    public AuthService(BCryptPasswordEncoder passwordEncoder,  UserRepository userRepository,
                       AuthenticationManager authenticationManager,  JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(@RequestBody AuthRequest authRequest){

        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setRole(Role.USER);
        //Saved Hashed Password
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(user.getUsername());
        authResponse.setId(user.getId());
        authResponse.setRole(user.getRole());
        return authResponse;
    }

    public String login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        System.out.println(token);
        return token;
    }
}
