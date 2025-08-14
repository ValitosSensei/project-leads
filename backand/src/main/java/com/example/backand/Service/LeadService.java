package com.example.backand.Service;

import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LeadService {
    LeadDTO createLead(LeadDTO dto);
    Page<LeadDTO> getLeads(Pageable pageable);
    LeadDTO getLeadById(Long id);
    void deleteLead(Long id);
    Page<LeadDTO> searchByName(String name, Pageable pageable);
    Page<LeadDTO> searchByType(String typeOfWork, Pageable pageable);
    Page<LeadDTO> searchByPhone(String phone, Pageable pageable);
}
