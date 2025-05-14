package UniversityCourseMgmtSystem;

public class Student {
    private String studentId;
    private String studentName;
    private static int studentCount=0;

    public Student(String studentName) {
        this.studentName = studentName;
        studentCount++;
        this.studentId=String.format("S%03d",studentCount);
        System.out.println("Student Registered: "+studentName+" (ID: "+studentId +")");
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}
