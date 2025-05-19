package com.kiselev.userbalance.adapter.elastic;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "users")
public class UserSearchDocument {
    @Id
    private Long id;

    private String name;

    private List<String> emails;

    private List<String> phones;
}