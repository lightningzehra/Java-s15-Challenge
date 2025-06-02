package com.librarysystem.people;
import com.librarysystem.book.Book;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person{
    private List<Book> books;

    public Author(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public void newBook(Book book){
        books.add(book);
    }

    public void showBook(){
        System.out.println(getName() + " kitaplarının listesi:");
        for (Book book : books){
            System.out.println(" - " + book.getTitle());
        }
    }

    @Override
    public String whoYouAre() {
        return "Author: " + getName();
    }

    @Override
    public String toString() {
        return getName(); //örneğin storybok kısmında yazar ismi görünmesi adına persondaki getname metodunu çağıracak vurada to string ile override ettim
    }
}
