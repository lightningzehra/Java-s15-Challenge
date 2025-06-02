package com.librarysystem.people;

import com.librarysystem.book.Book;
import com.librarysystem.library.Barrowable;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person implements Barrowable {
    private List<Book> barrowedBooks;
    private List<Book> purchasedBooks;



    public Reader(String name, List<Book> barrowedBooks, List<Book> purchasedBooks) {
        super(name);
        this.barrowedBooks = new ArrayList<>();  //  this.barrowedBooks = barrowedBooks; neden böyle yapmadık???????
        this.purchasedBooks = new ArrayList<>();
    }

    public Reader(String name){
        super(name);
    }

    //ödünc alınan kitap
    @Override
    public void borrowBook(Book book) {
        barrowedBooks.add(book);
    }

    //iade edilen kitap
    @Override
    public void returnBook(Book book) {
        barrowedBooks.remove(book);
    }

    //satın alınan kitap
    public void purchaseBook(Book book) {
        purchasedBooks.add(book);
    }

    @Override
    public String whoYouAre() {
        return "Reader: " + getName();
    }

    @Override
    public String toString() {
        return "Reader{" +
                getName() +
                '}';
    }
}
