package com.example.crm_system.service;

import com.example.crm_system.dto.ApplicationRequestDTO;
import com.example.crm_system.entity.ApplicationRequest;
import com.example.crm_system.entity.Operator;
import com.example.crm_system.mapper.ApplicationRequestMapper;
import com.example.crm_system.repository.ApplicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService {

    private final ApplicationRequestRepository applicationRequestRepository;
    private final ApplicationRequestMapper applicationRequestMapper;

    public List<ApplicationRequestDTO> getAllRequests() {
        return applicationRequestMapper.toDtoList(
                applicationRequestRepository.findAllByOrderByCreatedAtDesc()
        );
    }

    public List<ApplicationRequestDTO> getNewRequests() {
        return applicationRequestMapper.toDtoList(
                applicationRequestRepository.findByHandled(false)
        );
    }

    public List<ApplicationRequestDTO> getHandledRequests() {
        return applicationRequestMapper.toDtoList(
                applicationRequestRepository.findByHandled(true)
        );
    }

    public ApplicationRequestDTO getRequestById(String id) {
        ApplicationRequest request = applicationRequestRepository.findById(id).orElse(null);
        return request != null ? applicationRequestMapper.toDto(request) : null;
    }

    public ApplicationRequestDTO addRequest(ApplicationRequestDTO requestDTO) {
        ApplicationRequest request = applicationRequestMapper.toEntity(requestDTO);
        request.setCreatedAt(LocalDateTime.now());
        request.setHandled(false);
        ApplicationRequest saved = applicationRequestRepository.save(request);
        return applicationRequestMapper.toDto(saved);
    }

    public ApplicationRequestDTO updateRequest(String id, ApplicationRequestDTO requestDetails) {
        ApplicationRequest request = applicationRequestRepository.findById(id).orElse(null);
        if (request != null) {
            request.setUserName(requestDetails.getUserName());
            request.setCommentary(requestDetails.getCommentary());
            request.setPhone(requestDetails.getPhone());
            request.setHandled(requestDetails.isHandled());
            ApplicationRequest updated = applicationRequestRepository.save(request);
            return applicationRequestMapper.toDto(updated);
        }
        return null;
    }

    public ApplicationRequestDTO handleRequest(String id, List<Operator> operators) {
        ApplicationRequest request = applicationRequestRepository.findById(id).orElse(null);
        if (request != null) {
            request.setHandled(true);
            request.getOperators().addAll(operators);
            ApplicationRequest saved = applicationRequestRepository.save(request);
            return applicationRequestMapper.toDto(saved);
        }
        return null;
    }

    public void deleteRequest(String id) {
        applicationRequestRepository.deleteById(id);
    }

    public ApplicationRequest saveRequest(ApplicationRequest request) {
        return applicationRequestRepository.save(request);
    }
    public ApplicationRequestDTO removeOperatorFromRequest(String requestId, String operatorId) {
        ApplicationRequest request = applicationRequestRepository.findById(requestId).orElse(null);
        if (request != null) {
            request.getOperators().removeIf(op -> op.getId().equals(operatorId));
            ApplicationRequest saved = applicationRequestRepository.save(request);
            return applicationRequestMapper.toDto(saved);
        }
        return null;
    }



    public ApplicationRequest getRequestEntityById(String id) {
        return applicationRequestRepository.findById(id).orElse(null);
    }
}