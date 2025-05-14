package UniversityCourseMgmtSystem;

public class EnrollmentManager {
    public void enroll(Student student, Course course){
        System.out.print("Enrolling "+student.getStudentName()+" in "+course.getCourseId()+"...");
        boolean canEnroll= course.enrollStudent();
        if(canEnroll){
            System.out.println("Success");
        }
        else{
            System.out.println("Failed, Course is Full");
        }
    }
}
