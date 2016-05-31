package pl.bookstore.robot.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String category;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private BookStore bookStore;
    private Date data=new Date();

    public Book(String title) {
        this.title = title;
        this.bookStore=new BookStore();
    }

    public Book(String title, String category) {
        this.title = title;
        this.category = category;
        this.bookStore=new BookStore();
    }

    public Book(String title, String category, BookStore bookStore) {
        this.title = title;
        this.category = category;
        this.bookStore = bookStore;
    }

    public Book() {
        this.bookStore=new BookStore();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public BookStore getBookStore() {
        return bookStore;
    }

    public void setBookStore(BookStore bookStore) {
        bookStore.addBook(this);
        this.bookStore = bookStore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
         return category != null ? category.equals(book.category) : book.category == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", bookStoreName='" + bookStore.getName() + '\'' +
                '}';
    }
}
