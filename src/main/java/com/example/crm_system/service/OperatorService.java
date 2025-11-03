package com.example.crm_system.service;

import com.example.crm_system.entity.Operator;
import com.example.crm_system.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public Operator getOperatorById(String id) {
        return operatorRepository.findById(id).orElse(null);
    }

    public List<Operator> getOperatorsByIds(List<String> ids) {
        return operatorRepository.findAllById(ids);
    }

    public Operator addOperator(Operator operator) {
        return operatorRepository.save(operator);
    }
}