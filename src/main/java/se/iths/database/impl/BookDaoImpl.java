package se.iths.database.impl;

import se.iths.database.Book;
import se.iths.database.dao.BookDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

public class BookDaoImpl implements BookDao {
    EntityManagerFactory emf;
    EntityManager em;

    public BookDaoImpl() {
        this.emf = Persistence.createEntityManagerFactory("DatabaseLab4");
        this.em = emf.createEntityManager();
    }

    @Override
    public void insert(Book book) {
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Book book) {
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
    }

    @Override
    public void update(Book book) {
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }

    @Override
    public Book getByID(int bookId) {
        return em.find(Book.class, bookId);
    }

    @Override
    public List<Book> getByName(String name) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE name LIKE :name", Book.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> getByAuthor(String author) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE author LIKE :author", Book.class);
        query.setParameter("author", "%" + author + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> getByPrice(float min, float max) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE price BETWEEN :min AND :max", Book.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    @Override
    public List<Book> getByGenre(String genre) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE genre LIKE :genre", Book.class);
        query.setParameter("genre", "%" + genre + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> getByReleaseDate(Date min, Date max) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE releaseDate BETWEEN :min AND :max", Book.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    @Override
    public List<Book> getIfInStock() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE inStock = true", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}
