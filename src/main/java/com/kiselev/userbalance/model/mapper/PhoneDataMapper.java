package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.PhoneDataEntity;
import com.kiselev.userbalance.domain.PhoneData;
import com.kiselev.userbalance.model.dto.PhoneDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PhoneDataMapper {
    @Mapping(target = "userId", source = "userEntity.id")
    PhoneData toDomain(PhoneDataEntity entity);

    @Mapping(
            target = "userEntity",
            expression = "java(new com.kiselev.userbalance.adapter.entity.UserEntity(domain.getUserId()))"
    )
    PhoneDataEntity toEntity(PhoneData domain);

    PhoneDataDto toDto(PhoneData domain);

    PhoneData toDomain(PhoneDataDto dto);
}