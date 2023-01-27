package com.project.one.services;

import com.project.one.models.Book;
import com.project.one.models.Person;
import com.project.one.repositories.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(Integer page, Integer booksPerPage, Boolean sortByYear) {
        if (page != null && booksPerPage != null && sortByYear != null && sortByYear == true)
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        if (page != null && booksPerPage != null)
            return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        if (sortByYear != null && sortByYear == true) return bookRepository.findAll(Sort.by("year"));
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findOne(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setBook_id(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return Optional.ofNullable(findOne(id).getOwner());
    }

    @Transactional
    public void release(int id) {
        findOne(id).setOwner(null);
        findOne(id).setTakenDate(null);
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = findOne(id);
        book.setOwner(person);
        book.setTakenDate(new Date());
    }

    @Transactional
    public Book finByName(String bookName) {
        if (bookRepository.findByNameContainsIgnoreCase(bookName).isEmpty()) return null;
        return bookRepository.findByNameContainsIgnoreCase(bookName).get(0);
    }
}
