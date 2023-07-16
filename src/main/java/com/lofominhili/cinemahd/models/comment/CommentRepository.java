package com.lofominhili.cinemahd.models.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByFilmFilmIdOrderByCommentIdDesc(Long film_filmId, Pageable pageable);
}
