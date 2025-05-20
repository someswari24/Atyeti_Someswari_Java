package LibraryManagementSystem;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Librarian librarian=new Librarian("123","seetha");

        Book book1=new Book("The Story of My Experiments with Truth","Mahatma Gandhi");
        Book book2=new Book("The Discovery of India","Jawaharlal Nehru");
        Book book3=new Book("The Shadow Lines","Amitav Ghosh");
        Book book4=new Book("Five Point Someone","Chetan Bhagat");

        librarian.addBook(book1);
        librarian.addBook(book2);
        librarian.addBook(book3);
        librarian.addBook(book4);

        Member student=new StudentMember("s001","Ram",new ArrayList<>());
        Member faculty=new FacultyMember("F001","Rani",new ArrayList<>());

        librarian.addMember(student);
        librarian.addMember(faculty);

        System.out.println("All Books");
        librarian.viewAllBooks();

        boolean borrow=faculty.borrowBook(book1);
        System.out.println("Borrowed book:"+borrow);

        boolean borrow2=student.borrowBook(book1);
        System.out.println("borrowed book:"+borrow2);

        faculty.returnBook(book1);
        librarian.viewAllBooks();


    }
}
