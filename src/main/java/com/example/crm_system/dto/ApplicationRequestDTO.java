package com.example.crm_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequestDTO {
    private String id;
    private String userName;
    private String commentary;
    private String phone;
    private boolean handled;
    private LocalDateTime createdAt;
    private CourseDTO course;
    private List<OperatorDTO> operators = new ArrayList<>();
}