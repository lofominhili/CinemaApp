package com.lofominhili.cinemahd.controllers;

import com.lofominhili.cinemahd.dto.FilmDTO;
import com.lofominhili.cinemahd.dto.FilmRequest;
import com.lofominhili.cinemahd.dto.PaginationResponse;
import com.lofominhili.cinemahd.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/film")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("{film_id}")
    public FilmDTO getFilm(@PathVariable("film_id") long filmId) {
        return filmService.getFilm(filmId);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getFilms(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long genre) {

        System.out.println(genre);
        Page<FilmDTO> films;

        boolean hasSearch = search != null && !search.trim().isEmpty();
        boolean hasGenre = genre != null;

        if (hasGenre && hasSearch)
            films = filmService.getFilms(genre, search, PageRequest.of(page, size));

        else if (hasSearch)
            films = filmService.getFilms(search, PageRequest.of(page, size));

        else if (hasGenre)
            films = filmService.getFilms(genre, PageRequest.of(page, size));

        else
            films = filmService.getFilms(PageRequest.of(page, size));


        return ResponseEntity.ok(new PaginationResponse<>(films));
    }

    @PostMapping
    public FilmDTO addFilm(@RequestBody FilmRequest film) {
        return filmService.addFilm(film.toFilmWithoutGenre(), film.getGenres());
    }

    //TODO
    @PostMapping("{film_id}/favorite/{user_id}")
    public ResponseEntity<?> addFilmToFavorite(
            @PathVariable("film_id") long filmId,
            @PathVariable("user_id") long userId) {
        return ResponseEntity.ok(
                filmService.addFilmToFavorite(filmId, userId));
    }

    //TODO
    @DeleteMapping("{film_id}/favorite/{user_id}")
    public ResponseEntity<?> removeFilmFromFavorite(
            @PathVariable("film_id") long filmId,
            @PathVariable("user_id") long userId) {
        return ResponseEntity.ok(
                filmService.removeFilmFromFavorite(filmId, userId));
    }
}
