package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private String librarianId;
    private String librarianName;

    private List<Book> books ;
    private List<Member> members;


    public Librarian(String librarianName, String librarianId) {
        this.librarianName = librarianName;
        this.librarianId = librarianId;
        this.books=new ArrayList<>();
        this.members=new ArrayList<>();
    }

    public String getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(String librarianId) {
        this.librarianId = librarianId;
    }

    public String getLibrarianName() {
        return librarianName;
    }

    public void setLibrarianName(String librarianName) {
        this.librarianName = librarianName;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public void viewAllBooks(){
        for (Book book:books){
            System.out.println(book);
        }
    }

    public void viewAllMembers(){
        for(Member member:members){
            System.out.println(member);
        }
    }

    public void addMember(Member member){
        members.add(member);
    }
}
