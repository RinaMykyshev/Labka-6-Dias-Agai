package com.example.crm_system.repository;

import com.example.crm_system.entity.ApplicationRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRequestRepository extends MongoRepository<ApplicationRequest, String> {
    List<ApplicationRequest> findByHandled(boolean handled);
    List<ApplicationRequest> findAllByOrderByCreatedAtDesc();
}