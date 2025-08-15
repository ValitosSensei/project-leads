package com.example.backand.Controller;

import com.example.backand.Config.JwtTokenProvider;
import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadDTO;
import com.example.backand.DTO.LeadSearchRequestDTO;
import com.example.backand.Service.LeadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping("/admin/leads/search")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<LeadDTO> searchLeads(@RequestBody LeadSearchRequestDTO request,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return leadService.searchLead(request, pageable);
    }

}
