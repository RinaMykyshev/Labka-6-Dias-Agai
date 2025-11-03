package com.example.crm_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "application_requests")
public class ApplicationRequest {
    @Id
    private String id;
    private String userName;
    private String commentary;
    private String phone;
    private boolean handled;
    private LocalDateTime createdAt;

    @DBRef
    private Courses course;

    @DBRef
    private List<Operator> operators = new ArrayList<>();
}