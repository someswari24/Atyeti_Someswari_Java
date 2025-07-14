package onlineCourseMgmt.onlineCourseManagement.controller;

import lombok.RequiredArgsConstructor;
import onlineCourseMgmt.onlineCourseManagement.model.Instructor;
import onlineCourseMgmt.onlineCourseManagement.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(instructorService.createInstructor(instructor));
    }

    @GetMapping
    public List<Instructor> getAll() {
        return instructorService.getAllInstructors();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor updatedInstructor) {
        Instructor updated = instructorService.updateInstructor(id, updatedInstructor);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }

}

