package com.kiselev.userbalance.adapter.sql.mapper;

import com.kiselev.userbalance.adapter.sql.entity.EmailDataEntity;
import com.kiselev.userbalance.domain.EmailData;
import com.kiselev.userbalance.port.rest.request.EmailDataRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmailDataMapper {
    @Mapping(target = "userId", source = "userEntity.id")
    EmailData toDomain(EmailDataEntity entity);

    EmailDataRequest toDto(EmailData domain);

    EmailData toDomain(EmailDataRequest dto);
}