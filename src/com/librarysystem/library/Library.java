package com.librarysystem.library;

import com.librarysystem.book.Book;
import com.librarysystem.people.Reader;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library(List<Book> books, List<Reader> readers) {
        this.books = books;
        this.readers = readers;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    //kütüphaneye kitap ekleme metodumuz
    public void addBook(Book book){
        if(book != null){
            books.add(book);
            System.out.println(book.getTitle() + " kitabı eklendi");
        } else {
            System.out.println("Kitap nesnesini giriniz");
        }
    }

    //kğtğphaneye okuyucu eklereme metodumuz
    public void addReader(Reader reader){
        if(reader != null){
            readers.add(reader);
            System.out.println(reader.getName() + " reader'ı eklendi");
        } else {
            System.out.println("Reader nesnesini giriniz");
        }
    }


    //kitap ödünç verme
    public void lendBook(Book book, Reader reader) {
        if (books.contains(book)) {
            books.remove(book);
            reader.borrowBook(book);
            System.out.println(reader.getName() + " kitabı ödünç aldı: " + book.getTitle());
        } else {
            System.out.println("Kitap mevcut değil: " + book.getTitle());
        }
    }

    //kitap iade alma
    public void takeBackBook(Book book, Reader reader) {
        reader.returnBook(book);
        books.add(book);
        System.out.println(reader.getName() + " kitabı iade etti: " + book.getTitle());
    }

    public void removeBook(Long bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            books.remove(book);
            System.out.println(book.getTitle() + " kitabı kütüphaneden silindi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }



    //kütüphanedeki mevcut kitapların listesi
    public void showBooks() {
        if(books.isEmpty()){
            System.out.println("Kütüphanede kitap bulunmamaktadır");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public Book findBookById(Long bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookByName(String name) {
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(name)) {
                return book;
            }
        }
        return null;
    }


    //gecikme cezası hesaplama
    public double calculateFine(LocalDate dueDate) {
        LocalDate today = LocalDate.now();
        long daysLate = ChronoUnit.DAYS.between(dueDate, today); //günlerin farkını alır hesaplamak için
        if (daysLate > 0) {
            double fine = daysLate * 5.0;
            System.out.println("Gecikme süresi: " + daysLate + " gün | Ceza: " + fine + " TL");
            return fine;
        }
        System.out.println("Gecikme yok. Ceza: 0 TL");
        return 0;
    }

    //Fatura oluşturma metodu (Ödünç süresine bağlı olarak)
    public void createBill(Reader reader, double fineAmount) {
        System.out.println("📜 Fatura oluşturuldu: " + reader.getName() + " | Ceza: " + fineAmount + " TL");
    }


    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", readers=" + readers +
                '}';
    }
}