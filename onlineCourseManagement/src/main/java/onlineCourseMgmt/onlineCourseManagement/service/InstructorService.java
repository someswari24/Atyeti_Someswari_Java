package onlineCourseMgmt.onlineCourseManagement.service;

import lombok.RequiredArgsConstructor;
import onlineCourseMgmt.onlineCourseManagement.model.Instructor;
import onlineCourseMgmt.onlineCourseManagement.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor updateInstructor(Long id, Instructor updated) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        instructor.setName(updated.getName());
        instructor.setEmail(updated.getEmail());
        instructor.setSpecialization(updated.getSpecialization());
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}

