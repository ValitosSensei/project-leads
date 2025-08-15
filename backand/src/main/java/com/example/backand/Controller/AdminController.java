package com.example.backand.Controller;

import com.example.backand.Config.JwtTokenProvider;
import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadDTO;
import com.example.backand.DTO.LeadSearchRequestDTO;
import com.example.backand.Repository.LeadRepository;
import com.example.backand.Service.LeadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final LeadServiceImpl leadService;
    private final LeadRepository leadRepository;

    @Autowired
    public AdminController( LeadServiceImpl leadService,  LeadRepository leadRepository) {
        this.leadService = leadService;
        this.leadRepository = leadRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/leads")
    public Page<LeadDTO> getLeads() {
        return  leadService.getLeads(PageRequest.of(0, 100));
    }




}
