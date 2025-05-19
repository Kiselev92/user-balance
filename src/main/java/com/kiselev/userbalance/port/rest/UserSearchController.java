package com.kiselev.userbalance.port.rest;

import com.kiselev.userbalance.adapter.elastic.UserSearchDocument;
import com.kiselev.userbalance.adapter.elastic.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class UserSearchController {

    private final UserSearchRepository userSearchRepository;

    @GetMapping("/name")
    public List<UserSearchDocument> searchByName(@RequestParam String q) {
        return userSearchRepository.findByNameContainingIgnoreCase(q);
    }
}