package onlineCourseMgmt.onlineCourseManagement.service;

import onlineCourseMgmt.onlineCourseManagement.model.Course;
import onlineCourseMgmt.onlineCourseManagement.model.Instructor;
import onlineCourseMgmt.onlineCourseManagement.model.Student;
import onlineCourseMgmt.onlineCourseManagement.repository.CourseRepository;
import onlineCourseMgmt.onlineCourseManagement.repository.InstructorRepository;
import onlineCourseMgmt.onlineCourseManagement.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepo;
    private final StudentRepository studentRepo;
    private final InstructorRepository instructorRepo;

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public void assignInstructor(Long courseId, Long instructorId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
        course.setInstructor(instructor);
        courseRepo.save(course);
    }

    public void enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        course.getEnrolledStudents().add(student);
        courseRepo.save(course);
    }

    public List<Student> getStudentsInCourse(Long courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"))
                .getEnrolledStudents();
    }

    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepo.findAll().stream()
                .filter(c -> c.getInstructor() != null && c.getInstructor().getId().equals(instructorId))
                .collect(Collectors.toList());
    }
}

