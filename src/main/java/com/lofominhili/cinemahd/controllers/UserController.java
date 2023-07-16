package com.lofominhili.cinemahd.controllers;

import com.lofominhili.cinemahd.dto.PaginationResponse;
import com.lofominhili.cinemahd.models.film.Film;
import com.lofominhili.cinemahd.models.user.User;
import com.lofominhili.cinemahd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{user_id}/favorite")
    public ResponseEntity<?> getFavorite(@PathVariable("user_id") long userId,
                                         @RequestParam int page,
                                         @RequestParam int size) {
        Page<Film> favorite = userService.getFavorite(userId, PageRequest.of(page, size));
        return ResponseEntity.ok(new PaginationResponse<>(favorite));
    }

    @GetMapping("{user_id}/info")
    public User getUser(@PathVariable("user_id") long userId) {
        return userService.findById(userId);
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user_id") long userId) {
        userService.delete(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }
}
