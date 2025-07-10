package onlineCourseMgmt.onlineCourseManagement.controller;

import lombok.RequiredArgsConstructor;
import onlineCourseMgmt.onlineCourseManagement.model.*;
import onlineCourseMgmt.onlineCourseManagement.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Course course) {
        if (course.getTitle() == null || course.getTitle().isBlank()) {
            return ResponseEntity.badRequest().body("Course title cannot be empty");
        }
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{courseId}/instructor/{instructorId}")
    public void assignInstructor(@PathVariable Long courseId, @PathVariable Long instructorId) {
        courseService.assignInstructor(courseId, instructorId);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public void enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.enrollStudent(courseId, studentId);
    }

    @GetMapping("/{courseId}/students")
    public List<Student> getEnrolledStudents(@PathVariable Long courseId) {
        return courseService.getStudentsInCourse(courseId);
    }

    @GetMapping("/instructor/{instructorId}")
    public List<Course> getCoursesByInstructor(@PathVariable Long instructorId) {
        return courseService.getCoursesByInstructor(instructorId);
    }
}