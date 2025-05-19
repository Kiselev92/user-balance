package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.PhoneDataEntity;
import com.kiselev.userbalance.adapter.entity.UserEntity;
import com.kiselev.userbalance.domain.PhoneData;
import com.kiselev.userbalance.model.dto.PhoneDataDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T20:59:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class PhoneDataMapperImpl implements PhoneDataMapper {

    @Override
    public PhoneData toDomain(PhoneDataEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneData.PhoneDataBuilder phoneData = PhoneData.builder();

        phoneData.userId( entityUserEntityId( entity ) );
        phoneData.id( entity.getId() );
        phoneData.phone( entity.getPhone() );

        return phoneData.build();
    }

    @Override
    public PhoneDataEntity toEntity(PhoneData domain) {
        if ( domain == null ) {
            return null;
        }

        PhoneDataEntity.PhoneDataEntityBuilder phoneDataEntity = PhoneDataEntity.builder();

        phoneDataEntity.id( domain.getId() );
        phoneDataEntity.phone( domain.getPhone() );

        phoneDataEntity.userEntity( new com.kiselev.userbalance.adapter.entity.UserEntity(domain.getUserId()) );

        return phoneDataEntity.build();
    }

    @Override
    public PhoneDataDto toDto(PhoneData domain) {
        if ( domain == null ) {
            return null;
        }

        Long id = null;
        Long userId = null;
        String phone = null;

        id = domain.getId();
        userId = domain.getUserId();
        phone = domain.getPhone();

        PhoneDataDto phoneDataDto = new PhoneDataDto( id, userId, phone );

        return phoneDataDto;
    }

    @Override
    public PhoneData toDomain(PhoneDataDto dto) {
        if ( dto == null ) {
            return null;
        }

        PhoneData.PhoneDataBuilder phoneData = PhoneData.builder();

        phoneData.id( dto.id() );
        phoneData.userId( dto.userId() );
        phoneData.phone( dto.phone() );

        return phoneData.build();
    }

    private Long entityUserEntityId(PhoneDataEntity phoneDataEntity) {
        if ( phoneDataEntity == null ) {
            return null;
        }
        UserEntity userEntity = phoneDataEntity.getUserEntity();
        if ( userEntity == null ) {
            return null;
        }
        Long id = userEntity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
