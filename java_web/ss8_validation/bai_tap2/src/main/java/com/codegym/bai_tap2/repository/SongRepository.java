package com.codegym.bai_tap2.repository;

import com.codegym.bai_tap2.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
