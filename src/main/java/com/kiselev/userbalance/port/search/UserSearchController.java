package com.kiselev.userbalance.port.search;

import com.kiselev.userbalance.adapter.repository.UserSearchRepository;
import com.kiselev.userbalance.model.dto.search.UserSearchDocument;
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