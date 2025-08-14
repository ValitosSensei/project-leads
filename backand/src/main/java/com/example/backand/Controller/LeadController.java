package com.example.backand.Controller;

import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadDTO;
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

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<LeadDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String typeOfWork,
            @RequestParam(required = false) String phone,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size ,  Sort.by("createdAt").descending());

        Page<LeadDTO> result;

         if(name != null && typeOfWork != null){
            result = leadService.searchByNameAndTypeOfWork(name,typeOfWork,pageable);
        }
        else if(name != null){
            result = leadService.searchByName(name,pageable);
        }
        else if(phone != null){
            result = leadService.searchByPhone(phone,pageable);
        }
        else if(typeOfWork != null){
            result = leadService.searchByType(typeOfWork,pageable);
        }
        else{
            result = leadService.getLeads(pageable);
        }
        return ResponseEntity.ok(result);
    }


}
