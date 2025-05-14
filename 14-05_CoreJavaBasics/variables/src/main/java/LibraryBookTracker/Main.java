package LibraryBookTracker;

public class Main {
    public static void main(String[] args) {
        Book book1=new Book("c","Dennis Ritchie",false);
        Book book2=new Book("Java","James Josling",false);
        book1.issueBook();

        System.out.println(book1);
        System.out.println(book2);

        System.out.println("Total books created:"+Book.totalBooks);
    }
}
