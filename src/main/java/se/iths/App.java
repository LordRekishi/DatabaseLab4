package se.iths;

import se.iths.database.Book;
import se.iths.database.dao.BookDao;
import se.iths.database.impl.BookDaoImpl;

import java.sql.Date;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookDao bookDao = new BookDaoImpl();

    public static void main(String[] args) {
        startUp();
    }

    public static void startUp() {
        System.out.println("""
                Welcome to Laboration 4
                By: Patrik Fallqvist Magnusson
                                
                INSTRUCTIONS:
                1. Connect to your LOCALHOST server using Port 3306
                2. Change the USER and PASSWORD in the src/main/resources/META-INF/persistence.xml file
                2. Create a database called DatabaseLab4 in MySQL Workbench
                GOOD WORK!
                                
                Is this your first time running this program? (Y/N)""");

        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("\nRunning First time Setup...");

            insertDemoBooks();

            System.out.println("\nFirst time Setup completed!");
        } else {
            bookDao.truncateTable();
            insertBooks();
        }

        System.out.println("\nRunning Tests...");

        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();

        System.out.println("""
                
                All Tests Completed Successfully!
                                
                Program finished, thank you!
                Exiting...""");
    }

    private static void test10() {
        System.out.println("\nTest 10: Delete book with ID = 1 in Book table");
        System.out.println("Books before Delete");
        bookDao.getAll().forEach(System.out::println);
        bookDao.delete(bookDao.getByID(1));
        System.out.println("Books after Delete");
        bookDao.getAll().forEach(System.out::println);
        System.out.println("Test 10 completed!");
    }

    private static void test9() {
        System.out.println("\nTest 9: Update book with ID = 1, price 199 -> 399 in Book table");
        Book temp = bookDao.getByID(1);
        System.out.println("Book before Update: " + temp.toString());
        temp.setPrice(399);
        bookDao.update(temp);
        System.out.println("Book after Update: " + temp);
        System.out.println("Test 9 completed!");
    }

    private static void test8() {
        System.out.println("\nTest 8: Get if In Stock = TRUE in Book table");
        bookDao.getIfInStock().forEach(System.out::println);
        System.out.println("Test 8 completed!");
    }

    private static void test7() {
        System.out.println("\nTest 7: Get By Release Date, min = 1980-01-01, max = 2020-05-01 in Book table");
        bookDao.getByReleaseDate(Date.valueOf("1980-01-01"), Date.valueOf("2020-05-01")).forEach(System.out::println);
        System.out.println("Test 7 completed!");
    }

    private static void test6() {
        System.out.println("\nTest 6: Get By Genre = \"Science Fiction\" in Book table");
        bookDao.getByGenre("Science Fiction").forEach(System.out::println);
        System.out.println("Test 6 completed!");
    }

    private static void test5() {
        System.out.println("\nTest 5: Get By Price, min = 50, max = 300 in Book table");
        bookDao.getByPrice(50, 300).forEach(System.out::println);
        System.out.println("Test 5 completed!");
    }

    private static void test4() {
        System.out.println("\nTest 4: Get By Author = \"Asimov\" in Book table");
        bookDao.getByAuthor("Asimov").forEach(System.out::println);
        System.out.println("Test 4 completed!");
    }

    private static void test3() {
        System.out.println("\nTest 3: Get By Name = \"ti\" in Book table");
        bookDao.getByName("ti").forEach(System.out::println);
        System.out.println("Test 3 completed!");
    }

    private static void test2() {
        System.out.println("\nTest 2: Get By ID = 1 in Book table");
        System.out.println(bookDao.getByID(1));
        System.out.println("Test 2 completed!");
    }

    private static void test1() {
        System.out.println("\nTest 1: Get All in Book table");
        bookDao.getAll().forEach(System.out::println);
        System.out.println("Test 1 completed!");
    }

    private static void insertDemoBooks() {
        System.out.println("\nInserting Books...");
        insertBooks();
        System.out.println("Books inserted!");
    }

    private static void insertBooks() {
        Book book1 = new Book("Dune", "Frank Herbert", 199, "Science Fiction", Date.valueOf("1990-01-01"), true);
        bookDao.insert(book1);
        Book book2 = new Book("Foundation Trilogy", "Isaac Asimov", 399, "Science Fiction", Date.valueOf("2020-03-01"), true);
        bookDao.insert(book2);
        Book book3 = new Book("Beautiful World, Where Are You", "Sally Rooney", 99, "Fiction", Date.valueOf("2021-09-07"), false);
        bookDao.insert(book3);
        Book book4 = new Book("Inferno i snö", "Marie Bengts", 499, "Crime", Date.valueOf("2021-10-05"), true);
        bookDao.insert(book4);
        Book book5 = new Book("Nordiska väsen", "Johan Egerkrans", 299, "Non-Fiction", Date.valueOf("2013-08-05"), false);
        bookDao.insert(book5);
    }
}
