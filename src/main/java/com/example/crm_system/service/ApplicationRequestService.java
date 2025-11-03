package com.example.crm_system.service;

import com.example.crm_system.entity.ApplicationRequest;
import com.example.crm_system.entity.Operator;
import com.example.crm_system.repository.ApplicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService {

    private final ApplicationRequestRepository applicationRequestRepository;

    public List<ApplicationRequest> getAllRequests() {
        return applicationRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<ApplicationRequest> getNewRequests() {
        return applicationRequestRepository.findByHandled(false);
    }

    public List<ApplicationRequest> getHandledRequests() {
        return applicationRequestRepository.findByHandled(true);
    }

    public ApplicationRequest getRequestById(String id) {
        return applicationRequestRepository.findById(id).orElse(null);
    }

    public ApplicationRequest addRequest(ApplicationRequest request) {
        request.setCreatedAt(LocalDateTime.now());
        request.setHandled(false);
        return applicationRequestRepository.save(request);
    }

    public ApplicationRequest handleRequest(String id, List<Operator> operators) {
        ApplicationRequest request = getRequestById(id);
        if (request != null) {
            request.setHandled(true);
            request.getOperators().addAll(operators);
            return applicationRequestRepository.save(request);
        }
        return null;
    }

    public void deleteRequest(String id) {
        applicationRequestRepository.deleteById(id);
    }

    public ApplicationRequest removeOperatorFromRequest(String requestId, String operatorId) {
        ApplicationRequest request = getRequestById(requestId);
        if (request != null) {
            request.getOperators().removeIf(op -> op.getId().equals(operatorId));
            return applicationRequestRepository.save(request);
        }
        return null;
    }
}