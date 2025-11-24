package com.example.crm_system.mapper;

import com.example.crm_system.config.MapStructConfig;
import com.example.crm_system.dto.CourseDTO;
import com.example.crm_system.entity.Courses;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface CourseMapper {
    CourseDTO toDto(Courses course);
    Courses toEntity(CourseDTO courseDTO);
    List<CourseDTO> toDtoList(List<Courses> courses);
    List<Courses> toEntityList(List<CourseDTO> courseDTOs);
}