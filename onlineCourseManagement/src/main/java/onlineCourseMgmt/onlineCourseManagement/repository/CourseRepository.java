package onlineCourseMgmt.onlineCourseManagement.repository;

import onlineCourseMgmt.onlineCourseManagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {}

