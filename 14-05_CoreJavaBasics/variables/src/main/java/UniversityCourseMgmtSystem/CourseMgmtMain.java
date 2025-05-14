package UniversityCourseMgmtSystem;

public class CourseMgmtMain {
    public static void main(String[] args) {
        Course course1=new Course("CS101","Data Structures",2);
        Course course2=new Course("MA101","Calculus",2);

        Student student1=new Student("Alice");
        Student student2=new Student("Bob");

        EnrollmentManager enrollmentManager=new EnrollmentManager();
        enrollmentManager.enroll(student1,course1);
        enrollmentManager.enroll(student2,course1);

        System.out.println("Total Courses: " + Course.getTotalCourses());
        System.out.println("University: " + course1.getUNIVERSITY_NAME());
    }
}
