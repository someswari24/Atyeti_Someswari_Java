package LibraryManagementSystem;

import java.util.List;

public class FacultyMember extends Member {
    public FacultyMember(String memberId, String memberName, List<Book> borrowedBooks) {
        super(memberId, memberName, borrowedBooks);
    }

    @Override
    public int getBorrowLimit() {
        return 5;
    }
}
