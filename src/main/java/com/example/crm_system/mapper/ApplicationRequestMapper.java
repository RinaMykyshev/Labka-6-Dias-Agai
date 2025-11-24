package com.example.crm_system.mapper;

import com.example.crm_system.config.MapStructConfig;
import com.example.crm_system.dto.ApplicationRequestDTO;
import com.example.crm_system.entity.ApplicationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class, uses = {CourseMapper.class, OperatorMapper.class})
public interface ApplicationRequestMapper {
    @Mapping(target = "course", source = "course")
    @Mapping(target = "operators", source = "operators")

//    public class ApplicationRequest {
//        private Course course;
//        private List<Operator> operators;
//    }
    ApplicationRequestDTO toDto(ApplicationRequest request);

    @Mapping(target = "course", source = "course")
    @Mapping(target = "operators", source = "operators")
    ApplicationRequest toEntity(ApplicationRequestDTO requestDTO);

    List<ApplicationRequestDTO> toDtoList(List<ApplicationRequest> requests);
    List<ApplicationRequest> toEntityList(List<ApplicationRequestDTO> requestDTOs);
}