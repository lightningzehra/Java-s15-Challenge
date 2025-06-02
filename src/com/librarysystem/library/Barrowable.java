package com.librarysystem.library;

import com.librarysystem.book.Book;

public interface Barrowable {
    //iade ve ödünç alınabilir kitaplar için interface yaptık

    public abstract void borrowBook(Book book);
    void returnBook(Book book);
}
