package com.example.crm_system.mapper;

import com.example.crm_system.config.MapStructConfig;
import com.example.crm_system.dto.OperatorDTO;
import com.example.crm_system.entity.Operator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface OperatorMapper {
    OperatorDTO toDto(Operator operator);
    Operator toEntity(OperatorDTO operatorDTO);
    List<OperatorDTO> toDtoList(List<Operator> operators);
    List<Operator> toEntityList(List<OperatorDTO> operatorDTOs);
}

// без маппинга кыргын код, тоесть add to entity, list operator arraylist дегендер кооп болат