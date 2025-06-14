package com.codegym.bai_tap2.service;

import com.codegym.bai_tap2.model.Song;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    List<Song> findAll();
    Optional<Song> findById(Long id);
    Song save(Song song);
    Song update(Song song);  // <- thêm mới
    void deleteById(Long id);
}
