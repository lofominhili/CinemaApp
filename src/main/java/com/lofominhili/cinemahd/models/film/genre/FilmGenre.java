package com.lofominhili.cinemahd.models.film.genre;

import com.lofominhili.cinemahd.models.film.Film;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "films_genres")
@Getter
@Setter
@NoArgsConstructor
public class FilmGenre {

    @Id
    @SequenceGenerator(
            name = "film_genre_id",
            sequenceName = "film_genre_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "film_genre_id"
    )
    @Column(name = "film_genre_id")
    private long id;

    @ManyToOne(optional = false)
    private Genre genre;

    @ManyToOne(optional = false)
    private Film film;

    public FilmGenre(Film film, Genre genre) {
        this.film = film;
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmGenre filmGenre = (FilmGenre) o;
        return id == filmGenre.id
                && genre.equals(filmGenre.genre)
                && film.equals(filmGenre.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre, film);
    }

    @Override
    public String toString() {
        return "FilmGenre{" +
                "id=" + id +
                ", genre=" + genre +
                ", film=" + film +
                '}';
    }
}
