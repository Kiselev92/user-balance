package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.EmailDataEntity;
import com.kiselev.userbalance.domain.EmailData;
import com.kiselev.userbalance.model.dto.EmailDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmailDataMapper {
    @Mapping(target = "userId", source = "userEntity.id")
    EmailData toDomain(EmailDataEntity entity);

    @Mapping(
            target = "userEntity",
            expression = "java(new com.kiselev.userbalance.adapter.entity.UserEntity(domain.getUserId()))"
    )
    EmailDataEntity toEntity(EmailData domain);

    EmailDataDto toDto(EmailData domain);

    EmailData toDomain(EmailDataDto dto);
}