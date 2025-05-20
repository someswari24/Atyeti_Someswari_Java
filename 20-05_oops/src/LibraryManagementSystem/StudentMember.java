package LibraryManagementSystem;

import java.util.List;

public class StudentMember extends Member {

    public StudentMember(String memberId, String memberName, List<Book> borrowedBooks) {
        super(memberId, memberName, borrowedBooks);
    }

    @Override
    public int getBorrowLimit() {
        return 3;
    }
}
