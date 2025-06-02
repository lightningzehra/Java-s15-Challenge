package com.librarysystem.book;
import com.librarysystem.people.Author;
import com.librarysystem.people.Person;

import java.time.LocalDate;

public class Magazines extends Book{
    public Magazines(Long bookId, Author author, String bookName, double bookPrice, String status, int edition, LocalDate dateOfPurchase, Person owner, LocalDate borrowDate, LocalDate dueDate) {
        super(bookId, author, bookName, bookPrice, status, edition, dateOfPurchase, owner, borrowDate, dueDate);
    }

    @Override
    public void display() {
        System.out.println("Magazin: " + getBookName() + " | Yazarı: " + getAuthor() + " | Durumu: " + getStatus());
    }
}