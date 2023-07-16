package com.lofominhili.cinemahd.dto;


import com.lofominhili.cinemahd.models.film.Film;
import com.lofominhili.cinemahd.models.film.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilmDTO {
    Film film;
    List<Genre> genres;
}
