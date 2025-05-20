package LibraryManagementSystem;

import java.util.UUID;

public class Book {
    private UUID bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.bookId=UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.isAvailable=true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void markAsBorrowed(){
        isAvailable=false;
    }

    public void markAsReturned(){
        isAvailable=true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
