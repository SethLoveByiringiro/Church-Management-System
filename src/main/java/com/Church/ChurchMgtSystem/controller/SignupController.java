package com.Church.ChurchMgtSystem.controller;

import com.Church.ChurchMgtSystem.model.Signup;
import com.Church.ChurchMgtSystem.payload.JwtResponse;
import com.Church.ChurchMgtSystem.payload.LoginRequest;
import com.Church.ChurchMgtSystem.repository.SignupRepo;
import com.Church.ChurchMgtSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class SignupController {

    @Autowired
    private SignupRepo signupRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Signup> addUser(@Valid @RequestBody Signup signup) {
        if (!signup.getPassword().equals(signup.getConfirmPassword())) {
            return ResponseEntity.badRequest().body(null);
        }

        String encryptedPassword = passwordEncoder.encode(signup.getPassword());
        signup.setPassword(encryptedPassword);

        Signup savedUser = signupRepo.save(signup);
        savedUser.setPassword(null);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/users")
    public List<Signup> getAllUsers() {
        List<Signup> users = signupRepo.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(jwt);
        return ResponseEntity.ok(jwtResponse);
    }
}
