package com.example.crm_system.service;

import com.example.crm_system.dto.CourseDTO;
import com.example.crm_system.entity.Courses;
import com.example.crm_system.mapper.CourseMapper;
import com.example.crm_system.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {

    private final CoursesRepository coursesRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> getAllCourses() {
        return courseMapper.toDtoList(coursesRepository.findAll());
    }

    public CourseDTO getCourseById(String id) {
        Courses course = coursesRepository.findById(id).orElse(null);
        return course != null ? courseMapper.toDto(course) : null;
    }

    public CourseDTO addCourse(CourseDTO courseDTO) {
        Courses course = courseMapper.toEntity(courseDTO);
        Courses saved = coursesRepository.save(course);
        return courseMapper.toDto(saved);
    }

    public CourseDTO updateCourse(String id, CourseDTO courseDTO) {
        Courses existingCourse = coursesRepository.findById(id).orElse(null);
        if (existingCourse != null) {
            existingCourse.setName(courseDTO.getName());
            existingCourse.setDescription(courseDTO.getDescription());
            existingCourse.setPrice(courseDTO.getPrice());
            Courses updated = coursesRepository.save(existingCourse);
            return courseMapper.toDto(updated);
        }
        return null;
    }

    public void deleteCourse(String id) {
        coursesRepository.deleteById(id);
    }

    public Courses getCourseEntityById(String id) {
        return coursesRepository.findById(id).orElse(null);
    }
}