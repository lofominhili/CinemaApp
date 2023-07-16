package com.lofominhili.cinemahd.controllers;

import com.lofominhili.cinemahd.dto.SignupRequest;
import com.lofominhili.cinemahd.models.user.User;
import com.lofominhili.cinemahd.models.user.UserRepository;
import com.lofominhili.cinemahd.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public AuthController(@Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        Map<String, Object> response = new HashMap<>();

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            response.put("success", false);
            response.put("message", "[ERROR] Username exists");
            return ResponseEntity.badRequest().body(response);
        }

        User user = new User(
                signupRequest.getEmail(),
                signupRequest.getUsername(),
                signupRequest.getPassword(),
                signupRequest.getSub_deadline(),
                signupRequest.getDate_of_birth());

        User userDB = userRepository.save(user);

        response.put("success", true);
        response.put("message", "User CREATED");
        response.put("user", userDB);
        return ResponseEntity.ok(response);
    }
}

