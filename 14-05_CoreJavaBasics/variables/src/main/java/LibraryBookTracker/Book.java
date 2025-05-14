package LibraryBookTracker;

public class Book {
    private String title;
    private String author;
    private boolean isIssued;
    static int totalBooks=0;

    public Book(String title, String author, boolean isIssued) {
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
        totalBooks++;
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

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public void issueBook(){
        isIssued=true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isIssued=" + isIssued +
                '}';
    }
}
