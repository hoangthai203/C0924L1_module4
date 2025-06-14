package com.codegym.bai_tap2.service;

import com.codegym.bai_tap2.model.Song;
import com.codegym.bai_tap2.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song update(Song song) {
        return songRepository.save(song); // save() sẽ update nếu id tồn tại
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
