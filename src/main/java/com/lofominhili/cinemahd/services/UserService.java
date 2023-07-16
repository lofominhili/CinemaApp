package com.lofominhili.cinemahd.services;

import com.lofominhili.cinemahd.models.film.Film;
import com.lofominhili.cinemahd.models.user.User;
import com.lofominhili.cinemahd.models.user.UserRepository;
import com.lofominhili.cinemahd.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new NoSuchElementException("USER with id='" + userId + "' does not exist"));
    }

    public User edit(long id, User newUser) {
        User oldUser = userRepository.getReferenceById(id);
        if (newUser.getEmail() != null) oldUser.setEmail(newUser.getEmail());
        if (newUser.getUsername() != null) oldUser.setUsername(newUser.getUsername());
        if (newUser.getPassword() != null) oldUser.setPassword(passwordEncoder.generate());
        if (newUser.getDateOfBirth() != null) oldUser.setDateOfBirth(newUser.getDateOfBirth());
        if (newUser.getAvatar() != null) oldUser.setAvatar(newUser.getAvatar());
        return userRepository.save(oldUser);
    }

    public void delete(long id) {
        User user = userRepository.getReferenceById(id);
        Set<Film> favoriteFilms = user.getFavoriteFilms();
        for (var film : favoriteFilms) {
            film.removeConnoisseurs(user);
        }
        userRepository.deleteById(id);
    }

    public Page<Film> getFavorite(long userId, Pageable pageable) {
        return userRepository.findByIdFetch(userId, PageRequest.of(0, 2));
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
