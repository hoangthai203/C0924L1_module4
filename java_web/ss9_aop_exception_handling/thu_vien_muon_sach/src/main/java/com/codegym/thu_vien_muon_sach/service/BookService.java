package com.codegym.thu_vien_muon_sach.service;

import com.codegym.thu_vien_muon_sach.exception.BookNotAvailableException;
import com.codegym.thu_vien_muon_sach.exception.InvalidCodeException;
import com.codegym.thu_vien_muon_sach.model.Book;
import com.codegym.thu_vien_muon_sach.model.BorrowCode;
import com.codegym.thu_vien_muon_sach.repository.BookRepository;
import com.codegym.thu_vien_muon_sach.repository.BorrowCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BorrowCodeRepository codeRepo;

    private final Random random = new Random();

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepo.findById(id).orElseThrow();
    }

    @Override
    public String borrowBook(Long id) {
        Book book = findById(id);
        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Book out of stock.");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepo.save(book);

        String code = String.format("%05d", random.nextInt(100000));
        BorrowCode borrowCode = new BorrowCode();
        borrowCode.setCode(code);
        borrowCode.setBook(book);
        codeRepo.save(borrowCode);

        return code;
    }

    @Override
    public void returnBook(String code) {
        BorrowCode borrowCode = codeRepo.findById(code).orElseThrow(() ->
                new InvalidCodeException("Invalid code."));
        Book book = borrowCode.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepo.save(book);
        codeRepo.delete(borrowCode);
    }
}

