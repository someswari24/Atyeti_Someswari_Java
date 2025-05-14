package UniversityCourseMgmtSystem;

public class Course {
    private String courseId;
    private String courseName;
    private int maxStudents;
    private int enrolledStudents;
    private static int totalCourses=0;
    private final String UNIVERSITY_NAME="Global University";

    public Course(String courseId, String courseName, int maxStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxStudents = maxStudents;
        this.enrolledStudents = 0;
        totalCourses++;
        System.out.println("Course Created: "+courseId +"- "+ courseName);
    }
    public boolean enrollStudent(){
        if(enrolledStudents<maxStudents){
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public String getCourseId() {
        return courseId;
    }

    public static int getTotalCourses() {
        return totalCourses;
    }

    public String getUNIVERSITY_NAME() {
        return UNIVERSITY_NAME;
    }
}
