import com.librarysystem.book.Book;
import com.librarysystem.book.Journals;
import com.librarysystem.book.Magazines;
import com.librarysystem.book.StudyBooks;
import com.librarysystem.library.Library;
import com.librarysystem.people.Author;
import com.librarysystem.people.Librarian;
import com.librarysystem.people.Reader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Library Management System");

        // Yazarları oluştur
        Author author1 = new Author("J.K. Rowling");
        Author author2 = new Author("George Orwell");

        // Kitapları oluştur
        Book book1 = new Journals(1L, author1, "Harry Potter ve Felsefe Taşı", 40.0, "Available", 1, LocalDate.of(2010, 11, 15), null, null, null);
        Book book2 = new Journals(2L, author1, "Harry Potter ve Sırlar Odası", 45.0, "Available", 2, LocalDate.of(2011, 3, 22), null, null, null);
        Book book3 = new Magazines(3L, author2, "1984", 30.0, "Available", 1, LocalDate.of(2009, 6, 18), null, null, null);
        Book book4 = new StudyBooks(4L, author2, "Hayvan Çiftliği", 25.0, "Available", 3, LocalDate.of(2008, 8, 5), null, null, null);

        // Kitapları yazara ekle
        author1.newBook(book1);
        author1.newBook(book2);
        author2.newBook(book3);
        author2.newBook(book4);

        // Kitapları kütüphaneye ekle
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        // Okuyucuları oluştur
        Reader reader1 = new Reader("Fatma");
        Reader reader2 = new Reader("Ayşe");

        // Kütüphane nesnesini oluştur
        List<Reader> readers = new ArrayList<>();
        readers.add(reader1);
        readers.add(reader2);

        Library library = new Library(books, readers);

        // Kütüphanedeki kitapları listele
        System.out.println("Kütüphanedeki Kitaplar:");
        library.showBooks();

        // Kütüphaneci oluştur
        Librarian librarian = new Librarian("Zehra", "12345", library);

        // Konsol ile işlem yapabilmek için Scanner kullan
        Scanner scanner = new Scanner(System.in);

        // Kullanıcı giriş menüsü
        System.out.println("=== Kütüphane Sistemi ===");
        System.out.println("1. Admin Girişi");
        System.out.println("2. Okuyucu Girişi");
        System.out.print("Seçiminiz: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Temizleme

        // Admin Girişi
        if (choice == 1) {
            System.out.print("Kullanıcı Adı (Admin): ");
            String username = scanner.nextLine();
            System.out.print("Şifre: ");
            String password = scanner.nextLine();

            // Admin doğrulama
            if (librarian.login(username, password)) {
                System.out.println("Admin Girişi Başarılı!");

                // Admin menüsü
                while (true) {
                    System.out.println("\n=== Admin Menüsü ===");
                    System.out.println("1. Kitapları Göster");
                    System.out.println("2. Kitap Ekle");
                    System.out.println("3. Kitap Ödünç Ver");
                    System.out.println("4. Kitap İade Al");
                    System.out.println("0. Çıkış");

                    System.out.print("Seçiminiz: ");
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine(); // Temizleme

                    switch (adminChoice) {
                        case 1:
                            library.showBooks();
                            break;
                        case 2:
                            addBook(library);
                            break;
                        case 3:
                            lendBook(library, librarian);
                            break;
                        case 4:
                            returnBook(library, librarian);
                            break;
                        case 0:
                            System.out.println("Çıkılıyor...");
                            return;
                        default:
                            System.out.println("Geçersiz seçim! Tekrar deneyin.");
                    }
                }
            } else {
                System.out.println("Giriş başarısız. Yanlış kullanıcı adı veya şifre.");
            }
        }
        // Okuyucu Girişi
        else if (choice == 2) {
            System.out.print("Okuyucu Adı: ");
            String readerName = scanner.nextLine();

            Reader reader = findReaderByName(library, readerName);
            if (reader != null) {
                System.out.println("Okuyucu Girişi Başarılı! Hoşgeldiniz, " + readerName);

                // Okuyucu menüsü
                while (true) {
                    System.out.println("\n=== Okuyucu Menüsü ===");
                    System.out.println("1. Kitapları Göster");
                    System.out.println("2. Kitap Ödünç Al");
                    System.out.println("3. Kitap İade Et");
                    System.out.println("0. Çıkış");

                    System.out.print("Seçiminiz: ");
                    int readerChoice = scanner.nextInt();
                    scanner.nextLine(); // Temizleme

                    switch (readerChoice) {
                        case 1:
                            library.showBooks();
                            break;
                        case 2:
                            lendBook(library, librarian);
                            break;
                        case 3:
                            returnBook(library, librarian);
                            break;
                        case 0:
                            System.out.println("Çıkılıyor...");
                            return;
                        default:
                            System.out.println("Geçersiz seçim! Tekrar deneyin.");
                    }
                }
            } else {
                System.out.println("Okuyucu bulunamadı.");
            }
        } else {
            System.out.println("Geçersiz seçim!");
        }
    }

    // Okuyucuyu isimle bulma
    public static Reader findReaderByName(Library library, String readerName) {
        for (Reader reader : library.getReaders()) {
            if (reader.getName().equalsIgnoreCase(readerName)) {
                return reader;
            }
        }
        return null;
    }

    // Kitap ekleme
    public static void addBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kitap Adı: ");
        String bookName = scanner.nextLine();
        System.out.print("Yazar: ");
        String authorName = scanner.nextLine();
        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        System.out.print("Baskı Sayısı: ");
        int edition = scanner.nextInt();
        scanner.nextLine(); // Temizleme

        Author author = new Author(authorName);
        Book newBook = new Journals(100L, author, bookName, price, "Available", edition, LocalDate.now(), null, null, null);
        library.addBook(newBook);
    }

    // Kitap ödünç verme
    public static void lendBook(Library library, Librarian librarian) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ödünç vermek istediğiniz kitabın adını girin: ");
        String bookName = scanner.nextLine();
        Book book = library.findBookByName(bookName);

        if (book != null) {
            System.out.print("Okuyucu adını girin: ");
            String readerName = scanner.nextLine();
            Reader reader = findReaderByName(library, readerName);

            if (reader != null) {
                librarian.issueBook(book, reader);
            } else {
                System.out.println("Okuyucu bulunamadı.");
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    // Kitap iade alma
    public static void returnBook(Library library, Librarian librarian) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("İade almak istediğiniz kitabın adını girin: ");
        String bookName = scanner.nextLine();
        Book book = library.findBookByName(bookName);

        if (book != null) {
            System.out.print("Okuyucu adını girin: ");
            String readerName = scanner.nextLine();
            Reader reader = findReaderByName(library, readerName);

            if (reader != null) {
                library.takeBackBook(book, reader);
            } else {
                System.out.println("Okuyucu bulunamadı.");
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }
}
