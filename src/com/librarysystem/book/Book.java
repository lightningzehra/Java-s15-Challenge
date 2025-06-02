package com.librarysystem.book;

import com.librarysystem.people.Author;
import com.librarysystem.people.Person;
import com.librarysystem.people.Reader;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Book {
    private Long bookId;
    private Author author;
    private String bookName;
    private double bookPrice;
    private String status;
    private int edition;
    private LocalDate dateOfPurchase;
    private Person owner;
    private LocalDate borrowDate; // Kitap ne zaman kiralandı?
    private LocalDate dueDate;    // Kitabın iade edilmesi gereken tarih


    public Book(Long bookId, Author author, String bookName, double bookPrice, String status, int edition, LocalDate dateOfPurchase, Person owner, LocalDate borrowDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.author = author;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.status = status;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = owner;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;

    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return bookName;
    }


    public Person getOwner() {
        return owner;
    }

    //kitabın sahibini değiştiren metot
    public void changeOwner(Reader newOwner, LocalDate borrowDate, LocalDate dueDate) {
        if (newOwner != null) {
            this.owner = newOwner;
            this.borrowDate = LocalDate.now();
            this.dueDate = dueDate;
            this.status = "Borrowed";
            System.out.println(bookName + " kitabı artık " + newOwner.getName() + " adlı kişide.");
        } else {
            this.owner = null;
            this.status = "Available";
            this.borrowDate = null;
            this.dueDate = null;
            System.out.println(bookName + " kitabı kütüphaneye geri döndü.");
        }
    }

    //kitabın mevcut durumu
    public String getStatus() {
        return status;
    }

    //kitabın durumunu güncellemek için kullanacağımız metot
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }


    //kitap bilgilerini görmek için çağıracağım metot
    public abstract void display();


    //kitapları bookid'ye göre karşılaştırmak amaçlı metot
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return Objects.equals(bookId, book.bookId);
    }

    //bookid'ye göre hash değer döndürür
    @Override
    public int hashCode() {
        return Objects.hashCode(bookId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "Kitap ID: " + bookId +
                ", Yazar: " + author +
                ", Kitap Adı: '" + bookName + '\'' +
                ", Kitap Fiyatı: " + bookPrice +
                ", Durumu: " + status +
                ", Baskı: " + edition + "." +
                ", Satıldığı Tarih: " + dateOfPurchase +
                '}';
    }
}
