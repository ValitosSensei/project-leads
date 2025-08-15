package com.example.backand.DTO;


import lombok.Data;

@Data
public class LeadSearchRequestDTO {
    private String name;
    private String typeOfWork;
    private String callStatus;
    private String sortBy = "createdAt";
    private String sortDirection = "DESC";
}
