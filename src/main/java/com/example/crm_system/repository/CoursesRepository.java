package com.example.crm_system.repository;

import com.example.crm_system.entity.Courses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends MongoRepository<Courses, String> {
}