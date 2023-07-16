package com.lofominhili.cinemahd.models.film.genre;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "Genre")
@Table(name = "genres")
@NoArgsConstructor
@Data
public class Genre {

    @Id
    @SequenceGenerator(
            name = "genre_id",
            sequenceName = "genre_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genre_id"
    )
    @Column(name = "genre_id")
    private long genreId;

    @Column(name = "genre_name")
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genreId == genre.genreId && genreName.equals(genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreId, genreName);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
