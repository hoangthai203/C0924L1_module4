package com.codegym.thu_vien_muon_sach.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private int viewCount = 0;

    @After("execution(* com.codegym.thu_vien_muon_sach.controller.*.*(..))")
    public void logView() {
        viewCount++;
        System.out.println("Total visits: " + viewCount);
    }

    @After("execution(* com.codegym.thu_vien_muon_sach.service.BookService.borrowBook(..)) || " +
            "execution(* com.codegym.thu_vien_muon_sach.service.BookService.returnBook(..))")
    public void logChange(JoinPoint jp) {
        System.out.println("Book state changed by: " + jp.getSignature());
    }
}

