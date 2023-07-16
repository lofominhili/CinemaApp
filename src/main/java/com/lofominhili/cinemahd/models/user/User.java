package com.lofominhili.cinemahd.models.user;

import com.lofominhili.cinemahd.models.comment.Comment;
import com.lofominhili.cinemahd.models.film.Film;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.YEARS;

@Entity(name = "User")
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id",
            sequenceName = "user_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id"
    )
    @Column(name = "user_id")
    private Long Id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_favorite_films",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private final Set<Film> favoriteFilms = new HashSet<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> userComments = new ArrayList<>();

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "login", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "sub_deadline", nullable = false)
    private LocalDate subscriptionDeadline;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Transient
    private int age;

    @Column(name = "avatar", columnDefinition = "text")
    private String avatar;

    public User(String email,
                String username,
                String password,
                LocalDate subscriptionDeadline,
                LocalDate dateOfBirth) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.subscriptionDeadline = subscriptionDeadline;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return (int) YEARS.between(dateOfBirth, LocalDate.now());
    }


    public void addFavorite(Film film) {
        this.favoriteFilms.add(film);
        film.getConnoisseurs().add(this);
    }

    public void removeFavorite(Film film) {
        this.favoriteFilms.remove(film);
        film.getConnoisseurs().remove(this);
    }

    public boolean isNull() {
        return this.username == null || this.email == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(Id, user.Id) && username.equals(user.username) && email.equals(user.email) && password.equals(user.password) && Objects.equals(subscriptionDeadline, user.subscriptionDeadline) && dateOfBirth.equals(user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, username, email, password, dateOfBirth);
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", login='" + email + '\'' +
                ", subscriptionDeadline=" + subscriptionDeadline +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
