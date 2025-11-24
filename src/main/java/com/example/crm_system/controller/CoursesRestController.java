package com.example.crm_system.controller;

import com.example.crm_system.dto.CourseDTO;
import com.example.crm_system.service.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesRestController {
    private final CoursesService coursesService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return coursesService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String id) {
        CourseDTO course = coursesService.getCourseById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) {
        return coursesService.addCourse(courseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String id, @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourse = coursesService.updateCourse(id, courseDTO);
        return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        CourseDTO course = coursesService.getCourseById(id);
        if (course != null) {
            coursesService.deleteCourse(id);
            return ResponseEntity.ok("Курс успешно удален");
        }
        return ResponseEntity.notFound().build();
    }
}