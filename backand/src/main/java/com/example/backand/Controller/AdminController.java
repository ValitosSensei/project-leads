package com.example.backand.Controller;

import com.example.backand.Config.JwtTokenProvider;
import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadDTO;
import com.example.backand.Service.LeadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final LeadServiceImpl leadService;

    @Autowired
    public AdminController( LeadServiceImpl leadService) {
        this.leadService = leadService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/leads")
    public Page<LeadDTO> getLeads() {
        return  leadService.getLeads(PageRequest.of(0, 100));
    }

}
