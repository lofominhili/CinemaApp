package com.lofominhili.cinemahd.models.film;

import com.lofominhili.cinemahd.models.comment.Comment;
import com.lofominhili.cinemahd.models.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Entity(name = "Film")
@Table(name = "films")
@NoArgsConstructor
@Data
public class Film {

    @Id
    @SequenceGenerator(
            name = "film_id",
            sequenceName = "film_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "film_id"
    )

    @Column(name = "film_id")
    private long filmId;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteFilms")
    private final Set<User> connoisseurs = new HashSet<>();

    @Column(name = "film_link", nullable = false)
    String filmLink;

    @Column(name = "title", columnDefinition = "text", nullable = false, length = 30)
    private String title;

    @Column(name = "desc_rating")
    private double descRating;

    @Column(name = "desc_image", columnDefinition = "text")
    private String descImage;

    @Column(name = "desc_preview", columnDefinition = "text")
    private String descPreview;

    @Column(name = "desc_text", columnDefinition = "text")
    private String descText;

    public Film(String title,
                double descRating,
                String descImage,
                String descPreview,
                String descText,
                String filmLink) {
        this.title = title;
        this.descRating = descRating;
        this.descImage = descImage;
        this.descPreview = descPreview;
        this.descText = descText;
        this.filmLink = filmLink;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setFilm(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setFilm(null);
    }

    public void addConnoisseurs(User connoisseur) {
        connoisseurs.add(connoisseur);
        connoisseur.getFavoriteFilms().add(this);
    }

    public void removeConnoisseurs(User connoisseur) {
        connoisseurs.remove(connoisseur);
        if (connoisseur.isNull()) {
            connoisseur.getFavoriteFilms().remove(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmId == film.filmId && title.equals(film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title);
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", descRating=" + descRating +
                ", descImage='" + descImage + '\'' +
                ", descPreview='" + descPreview + '\'' +
                ", descText='" + descText + '\'' +
                '}';
    }
}
