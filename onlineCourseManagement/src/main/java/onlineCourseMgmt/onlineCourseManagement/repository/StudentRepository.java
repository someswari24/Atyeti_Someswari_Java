package onlineCourseMgmt.onlineCourseManagement.repository;

import onlineCourseMgmt.onlineCourseManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}

