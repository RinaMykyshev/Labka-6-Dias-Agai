package com.example.crm_system.controller;

import com.example.crm_system.dto.ApplicationRequestDTO;
import com.example.crm_system.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ApplicationRequestRestController {

    private final ApplicationRequestService applicationRequestService;

    @GetMapping
    public List<ApplicationRequestDTO> getAllRequests() {
        return applicationRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationRequestDTO> getRequestById(@PathVariable String id) {
        ApplicationRequestDTO request = applicationRequestService.getRequestById(id);
        return request != null ? ResponseEntity.ok(request) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ApplicationRequestDTO createRequest(@RequestBody ApplicationRequestDTO requestDTO) {
        return applicationRequestService.addRequest(requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationRequestDTO> updateRequest(@PathVariable String id,
                                                               @RequestBody ApplicationRequestDTO requestDetails) {
        ApplicationRequestDTO updatedRequest = applicationRequestService.updateRequest(id, requestDetails);
        return updatedRequest != null ? ResponseEntity.ok(updatedRequest) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable String id) {
        applicationRequestService.deleteRequest(id);
        return ResponseEntity.ok("Заявка успешно удалена");
    }
}