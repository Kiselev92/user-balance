package com.kiselev.userbalance.port;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import java.util.Map;
import com.kiselev.userbalance.PostgresContainerExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(PostgresContainerExtension.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NamedParameterJdbcTemplate testJdbc;

    @Test
    void find_user_by_id_when_user_exists() throws Exception {
        Long userId = 1000L;

        testJdbc.update("""
            INSERT INTO "user" (id, name, password, date_of_birth)
            VALUES (:id, :name, :password, :dob)
        """, Map.of(
                "id", userId,
                "name", "TestingUser",
                "password", "testingPassword123",
                "dob", LocalDate.of(1991, 1, 1)
        ));

        testJdbc.update("""
            INSERT INTO email_data (user_id, email)
            VALUES (:userId, :email)
        """, Map.of(
                "userId", userId,
                "email", "test122@example.com"
        ));

        testJdbc.update("""
            INSERT INTO phone_data (user_id, phone)
            VALUES (:userId, :phone)
        """, Map.of(
                "userId", userId,
                "phone", "12345678901"
        ));

        mockMvc.perform(get("/users/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("TestingUser"))
                .andExpect(jsonPath("$.dateOfBirth").value("1991-01-01"))
                .andExpect(jsonPath("$.emails[0]").value("test122@example.com"))
                .andExpect(jsonPath("$.phones[0]").value("12345678901"));
    }
}