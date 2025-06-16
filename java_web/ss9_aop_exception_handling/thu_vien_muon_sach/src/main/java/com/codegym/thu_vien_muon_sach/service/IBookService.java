package com.codegym.thu_vien_muon_sach.service;

import com.codegym.thu_vien_muon_sach.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
    String borrowBook(Long id);
    void returnBook(String code);
}

