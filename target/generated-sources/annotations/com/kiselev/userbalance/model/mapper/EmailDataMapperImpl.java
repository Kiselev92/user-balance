package com.kiselev.userbalance.model.mapper;

import com.kiselev.userbalance.adapter.entity.EmailDataEntity;
import com.kiselev.userbalance.adapter.entity.UserEntity;
import com.kiselev.userbalance.domain.EmailData;
import com.kiselev.userbalance.model.dto.EmailDataDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T20:59:50+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class EmailDataMapperImpl implements EmailDataMapper {

    @Override
    public EmailData toDomain(EmailDataEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EmailData.EmailDataBuilder emailData = EmailData.builder();

        emailData.userId( entityUserEntityId( entity ) );
        emailData.id( entity.getId() );
        emailData.email( entity.getEmail() );

        return emailData.build();
    }

    @Override
    public EmailDataEntity toEntity(EmailData domain) {
        if ( domain == null ) {
            return null;
        }

        EmailDataEntity.EmailDataEntityBuilder emailDataEntity = EmailDataEntity.builder();

        emailDataEntity.id( domain.getId() );
        emailDataEntity.email( domain.getEmail() );

        emailDataEntity.userEntity( new com.kiselev.userbalance.adapter.entity.UserEntity(domain.getUserId()) );

        return emailDataEntity.build();
    }

    @Override
    public EmailDataDto toDto(EmailData domain) {
        if ( domain == null ) {
            return null;
        }

        Long id = null;
        Long userId = null;
        String email = null;

        id = domain.getId();
        userId = domain.getUserId();
        email = domain.getEmail();

        EmailDataDto emailDataDto = new EmailDataDto( id, userId, email );

        return emailDataDto;
    }

    @Override
    public EmailData toDomain(EmailDataDto dto) {
        if ( dto == null ) {
            return null;
        }

        EmailData.EmailDataBuilder emailData = EmailData.builder();

        emailData.id( dto.id() );
        emailData.userId( dto.userId() );
        emailData.email( dto.email() );

        return emailData.build();
    }

    private Long entityUserEntityId(EmailDataEntity emailDataEntity) {
        if ( emailDataEntity == null ) {
            return null;
        }
        UserEntity userEntity = emailDataEntity.getUserEntity();
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
