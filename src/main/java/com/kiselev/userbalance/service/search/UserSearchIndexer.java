package com.kiselev.userbalance.service.search;

import com.kiselev.userbalance.adapter.entity.EmailDataEntity;
import com.kiselev.userbalance.adapter.entity.PhoneDataEntity;
import com.kiselev.userbalance.adapter.entity.UserEntity;
import com.kiselev.userbalance.adapter.repository.UserSearchRepository;
import com.kiselev.userbalance.model.dto.search.UserSearchDocument;
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