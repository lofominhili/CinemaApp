package com.lofominhili.cinemahd.models.comment;

import com.lofominhili.cinemahd.models.film.Film;
import com.lofominhili.cinemahd.models.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "Comment")
@Table(name = "comments")
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @SequenceGenerator(
            name = "comment_id",
            sequenceName = "comment_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_id"
    )
    private long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @Column(name = "text", nullable = false, columnDefinition = "text")
    private String text;

    @Column(name = "spoiler", nullable = false)
    private Boolean spoiler;

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "dislikes", nullable = false)
    private int dislikes = 0;

    public Comment(Film film,
                   User writer,
                   String text,
                   Boolean spoiler) {
        this.film = film;
        this.writer = writer;
        this.text = text;
        this.spoiler = spoiler;
    }

    public Comment(String comment, Boolean spoiler) {
        this.text = comment;
        this.spoiler = spoiler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", film=" + "\t" + film.getFilmId() + "\t" + film.getTitle() +
                ", writer=" + "\t" + writer.getId() + "\t" + writer.getEmail() +
                ", text='" + text + '\'' +
                ", spoiler=" + spoiler +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}