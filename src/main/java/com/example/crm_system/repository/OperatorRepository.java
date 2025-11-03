package com.example.crm_system.repository;

import com.example.crm_system.entity.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends MongoRepository<Operator, String> {
}