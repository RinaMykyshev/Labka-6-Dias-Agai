package com.example.crm_system.entity;

import com.example.crm_system.dto.OperatorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "operators")
public class Operator {
    @Id
    private String id;
    private String name;
    private String surname;
    private String department;


    public OperatorDTO toDto() {
        OperatorDTO dto = new OperatorDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setSurname(this.surname);
        dto.setDepartment(this.department);
        return dto;
    }


    public static Operator toEntity(OperatorDTO dto) {
        Operator operator = new Operator();
        operator.setId(dto.getId());
        operator.setName(dto.getName());
        operator.setSurname(dto.getSurname());
        operator.setDepartment(dto.getDepartment());
        return operator;
    }
}