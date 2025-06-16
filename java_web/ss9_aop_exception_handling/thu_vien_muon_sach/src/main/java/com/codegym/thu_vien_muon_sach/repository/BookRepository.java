package com.codegym.thu_vien_muon_sach.repository;

import com.codegym.thu_vien_muon_sach.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

