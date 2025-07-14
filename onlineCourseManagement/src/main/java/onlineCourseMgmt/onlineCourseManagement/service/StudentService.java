package onlineCourseMgmt.onlineCourseManagement.service;

import lombok.RequiredArgsConstructor;
import onlineCourseMgmt.onlineCourseManagement.model.Student;
import onlineCourseMgmt.onlineCourseManagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student updated) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(updated.getName());
        student.setEmail(updated.getEmail());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
