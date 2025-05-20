package LibraryManagementSystem;

import java.util.List;

public abstract class Member {
    private String memberId;
    private String memberName;
    private List<Book> borrowedBooks;
    public abstract int getBorrowLimit();

    public Member(String memberId, String memberName, List<Book> borrowedBooks) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.borrowedBooks = borrowedBooks;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public boolean borrowBook(Book book){
        if(book.isAvailable()&&borrowedBooks.size()<getBorrowLimit()) {
            borrowedBooks.add(book);
            book.markAsBorrowed();
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.markAsReturned();
            return true;
        }
        return false;
    }


}
