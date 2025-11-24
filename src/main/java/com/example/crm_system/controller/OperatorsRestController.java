package com.example.crm_system.controller;

import com.example.crm_system.dto.OperatorDTO;
import com.example.crm_system.entity.ApplicationRequest;
import com.example.crm_system.entity.Operator;
import com.example.crm_system.service.OperatorService;
import com.example.crm_system.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
public class OperatorsRestController {
    private final OperatorService operatorService;
    private final ApplicationRequestService applicationRequestService;

    @GetMapping
    public List<OperatorDTO> getAllOperators() {
        return operatorService.getAllOperators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatorDTO> getOperatorById(@PathVariable String id) {
        OperatorDTO operator = operatorService.getOperatorById(id);
        return operator != null ? ResponseEntity.ok(operator) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public OperatorDTO createOperator(@RequestBody OperatorDTO operatorDTO) {
        return operatorService.createOperator(operatorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperatorDTO> updateOperator(@PathVariable String id,
                                                      @RequestBody OperatorDTO operatorDTO) {
        OperatorDTO updatedOperator = operatorService.updateOperator(id, operatorDTO);
        return updatedOperator != null ? ResponseEntity.ok(updatedOperator) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperator(@PathVariable String id) {
        operatorService.deleteOperator(id);
        return ResponseEntity.ok("Оператор успешно удален");
    }

    @PutMapping("/{operatorId}/assign/{requestId}")
    public ResponseEntity<ApplicationRequest> assignOperatorToRequest(
            @PathVariable String operatorId,
            @PathVariable String requestId) {

        Operator operator = operatorService.getOperatorEntityById(operatorId);
        ApplicationRequest request = applicationRequestService.getRequestEntityById(requestId);

        if (operator != null && request != null) {
            request.getOperators().add(operator);
            request.setHandled(true);
            ApplicationRequest savedRequest = applicationRequestService.saveRequest(request);
            return ResponseEntity.ok(savedRequest);
        }
        return ResponseEntity.notFound().build();
    }
}