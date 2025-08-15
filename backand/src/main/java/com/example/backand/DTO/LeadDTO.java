package com.example.backand.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class LeadDTO {
    private Long id;

    @NotBlank(message = "Імя обовязкове")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Телефон обов'язковий")
    @Size(min = 5, max = 30)
    private String phone;

    @Size(max = 255)
    private String typeOfWork;

    @Size(max = 2000)
    private String comment;

    private LeadStatus status;
    private String adminComment;

    @JsonProperty("contactMethods")
    private Set<String> contactMethods = new HashSet<>();

    private LocalDateTime createdAt;

}
