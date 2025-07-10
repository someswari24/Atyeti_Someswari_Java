package onlineCourseMgmt.onlineCourseManagement.repository;

import onlineCourseMgmt.onlineCourseManagement.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {}
