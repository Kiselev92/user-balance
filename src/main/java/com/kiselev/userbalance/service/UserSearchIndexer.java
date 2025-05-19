package com.kiselev.userbalance.service;

import com.kiselev.userbalance.adapter.elastic.UserSearchDocument;
import com.kiselev.userbalance.adapter.elastic.UserSearchRepository;
import com.kiselev.userbalance.adapter.sql.entity.EmailDataEntity;
import com.kiselev.userbalance.adapter.sql.entity.PhoneDataEntity;
import com.kiselev.userbalance.adapter.sql.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSearchIndexer {

    private final UserSearchRepository userSearchRepository;

    public void indexUser(UserEntity user) {
        UserSearchDocument doc = UserSearchDocument.builder()
                .id(user.getId())
                .name(user.getName())
                .emails(user.getEmailDataEntities().stream().map(EmailDataEntity::getEmail).toList())
                .phones(user.getPhoneDataEntities().stream().map(PhoneDataEntity::getPhone).toList())
                .build();

        userSearchRepository.save(doc);
    }
}