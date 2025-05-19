package com.kiselev.userbalance.adapter.sql.mapper;

import com.kiselev.userbalance.adapter.sql.entity.PhoneDataEntity;
import com.kiselev.userbalance.domain.PhoneData;
import com.kiselev.userbalance.port.rest.request.PhoneDataRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PhoneDataMapper {
    @Mapping(target = "userId", source = "userEntity.id")
    PhoneData toDomain(PhoneDataEntity entity);

    PhoneDataRequest toDto(PhoneData domain);

    PhoneData toDomain(PhoneDataRequest dto);
}