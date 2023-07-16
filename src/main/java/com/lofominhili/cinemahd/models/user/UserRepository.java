package com.lofominhili.cinemahd.models.user;

import com.lofominhili.cinemahd.models.film.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Page<User> findAllByUsernameLike(String username, Pageable pageable);

    @Query("select c.favoriteFilms from User c join c.favoriteFilms where c.Id=?1")
    Page<Film> findByIdFetch(long userId, Pageable pageable);
}
