package com.example.crm_system.service;

import com.example.crm_system.dto.OperatorDTO;
import com.example.crm_system.entity.Operator;
import com.example.crm_system.mapper.OperatorMapper;
import com.example.crm_system.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;

    public List<OperatorDTO> getAllOperators() {
        return operatorMapper.toDtoList(operatorRepository.findAll());
    }

    public OperatorDTO getOperatorById(String id) {
        Operator operator = operatorRepository.findById(id).orElse(null);
        return operator != null ? operatorMapper.toDto(operator) : null;
    }

    public OperatorDTO createOperator(OperatorDTO operatorDTO) {
        Operator operator = operatorMapper.toEntity(operatorDTO);
        Operator saved = operatorRepository.save(operator);
        return operatorMapper.toDto(saved);
    }

    public OperatorDTO updateOperator(String id, OperatorDTO operatorDTO) {
        Operator existingOperator = operatorRepository.findById(id).orElse(null);
        if (existingOperator != null) {
            existingOperator.setName(operatorDTO.getName());
            existingOperator.setSurname(operatorDTO.getSurname());
            existingOperator.setDepartment(operatorDTO.getDepartment());
            Operator updated = operatorRepository.save(existingOperator);
            return operatorMapper.toDto(updated);
        }
        return null;
    }

    public void deleteOperator(String id) {
        operatorRepository.deleteById(id);
    }

    // Internal methods for entity operations
    public Operator getOperatorEntityById(String id) {
        return operatorRepository.findById(id).orElse(null);
    }

    public List<Operator> getOperatorsByIds(List<String> ids) {
        return operatorRepository.findAllById(ids);
    }

    public Operator addOperator(Operator operator) {
        return operatorRepository.save(operator);
    }
}