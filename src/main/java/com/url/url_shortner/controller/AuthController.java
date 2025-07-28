package com.url.url_shortner.controller;

import com.url.url_shortner.dto.LoginRequest;
import com.url.url_shortner.dto.RegisterRequest;
import com.url.url_shortner.model.User;
import com.url.url_shortner.security.jwt.JwtAuthenticationResponse;
import com.url.url_shortner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/public/login")
    public ResponseEntity<?>loginUser(@RequestBody LoginRequest loginRequest){
//        JwtAuthenticationResponse token = userService.loginUser(loginRequest);
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }
    @PostMapping("/public/register")
    public ResponseEntity<?>registerUser(@RequestBody RegisterRequest registerRequest ){
        User user=new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setRole("ROLE_USER");
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered Successfully");
    }
}
