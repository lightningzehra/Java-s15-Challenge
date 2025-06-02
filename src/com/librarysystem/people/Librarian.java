package com.librarysystem.people;

import com.librarysystem.book.Book;
import com.librarysystem.library.Library;


import java.time.LocalDate;
import java.util.List;

 public class Librarian extends Person {
        private String password;
        private Library library;

        public Librarian(String name, String password, Library library) {
            super(name);
            this.password = password;
            this.library = library;
        }


        //kitap arama
        public Book searchBook(String bookName) {
            List<Book> books = library.getBooks();
            for (Book book : books) {
                if (book.getBookName().equalsIgnoreCase(bookName)) {
                    System.out.println("Kitap bulundu: " + book.getBookName() + " | Durum: " + book.getStatus());
                    return book;
                }
            }
            System.out.println("Kitap bulunamadı!");
            return null;
        }


        //üyelik doğrulama
        public boolean verifyMember(Reader reader) {
            for (Reader reader1 : library.getReaders()) {
                if (reader1.equals(reader)) {
                    System.out.println(reader.getName() + " kütüphane üyesidir.");
                    return true;
                }
            }
            System.out.println(reader.getName() + " kütüphane üyesi değildir.");
            return false;
        }

        //kitabı ödünc verme
        public void issueBook(Book book, Reader reader) {
            if (book.getOwner() == null) { // Ödünç vermek için kitap kütüphaneye ait olmalı
                LocalDate borrowDate = LocalDate.now();  // Ödünç alma tarihi
                LocalDate dueDate = borrowDate.plusDays(14);  // 14 gün sonrası teslim tarihi

                book.changeOwner(reader, borrowDate, dueDate);  // Kitap sahibini değiştir
                book.updateStatus("Ödünç Verildi");
                System.out.println(reader.getName() + " kitabı aldı: " + book.getBookName());
            } else {
                System.out.println("Kitap zaten ödünç verilmiş!");
            }
        }

        public boolean login(String username, String password) {
            return username.equals(this.getName()) && password.equals(this.password);
        }

        @Override
        public String whoYouAre() {
            return "Librerian: " + getName();
        }

        @Override
        public String toString() {
            return "Librarian{" + "name= " + getName() +
                    " password='" + password + '\'' +
                    '}';
        }
    }

