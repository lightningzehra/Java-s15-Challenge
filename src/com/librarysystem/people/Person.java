package com.librarysystem.people;

//abstract sınıftır çünkü tüm insanlar için temel sınıf konumundadır

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String whoYouAre();
}