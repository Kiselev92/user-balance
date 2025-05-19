package com.kiselev.userbalance.adapter.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserSearchRepository extends ElasticsearchRepository<UserSearchDocument, Long> {
    List<UserSearchDocument> findByNameContainingIgnoreCase(String name);
}