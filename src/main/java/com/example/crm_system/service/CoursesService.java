package com.example.crm_system.service;

import com.example.crm_system.entity.Courses;
import com.example.crm_system.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {

    private final CoursesRepository coursesRepository;

    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    public Courses getCourseById(String id) {
        return coursesRepository.findById(id).orElse(null);
    }

    public Courses addCourse(Courses course) {
        return coursesRepository.save(course);
    }
}