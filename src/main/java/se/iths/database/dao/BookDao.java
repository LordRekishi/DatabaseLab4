package se.iths.database.dao;

import se.iths.database.Book;

import java.sql.Date;
import java.util.List;

public interface BookDao extends Dao<Book> {
    List<Book> getByAuthor(String author);
    List<Book> getByPrice(float min, float max);
    List<Book> getByGenre(String genre);
    List<Book> getByReleaseDate(Date min, Date max);
    List<Book> getIfInStock();
}
