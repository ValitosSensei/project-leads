package com.example.backand.Controller;

import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadDTO;
import com.example.backand.DTO.LeadStatus;
import com.example.backand.Service.LeadService;
import com.example.backand.Service.LeadServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/leads")
@CrossOrigin(origins = "http://localhost:5173")
public class LeadController {

    private final LeadServiceImpl leadService;

    @Autowired
    public LeadController(LeadServiceImpl leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<LeadDTO> create(@Valid @RequestBody LeadDTO dto) {
        LeadDTO created  =leadService.createLead(dto);
        URI location = URI.create("/api/leads/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<LeadDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<LeadDTO> leads = leadService.getLeads(pageable);
        return ResponseEntity.ok(leads);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LeadDTO> getLead(@PathVariable Long id) {
        return ResponseEntity.ok(leadService.getLeadById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }



    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LeadDTO> updateLeadStatus(
            @PathVariable Long id,
            @RequestParam LeadStatus status,
            @RequestParam(required = false) String adminComment) {
        return ResponseEntity.ok(leadService.updateStatusAndComment(id,status,adminComment));
    }


}
