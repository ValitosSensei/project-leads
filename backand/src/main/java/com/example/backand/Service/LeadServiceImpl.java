package com.example.backand.Service;

import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadDTO;
import com.example.backand.Repository.LeadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeadServiceImpl  implements  LeadService{

    private final LeadRepository leadRepository;

    @Autowired
    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Override
    @Transactional
    public LeadDTO createLead(LeadDTO dto) {
        Lead entity  = mapToEntity(dto);
        Lead saved = leadRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public Page<LeadDTO> getLeads(Pageable pageable) {
        Page<Lead> page = leadRepository.findAll(pageable);
        return page.map(this::mapToDTO);
    }

    @Override
    public LeadDTO getLeadById(Long id) {
        Lead lead  = leadRepository.findById(id).orElseThrow(()-> new RuntimeException("Lead not founded"));
        return mapToDTO(lead);
    }

    @Override
    @Transactional
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }
    //Мапування дто в ліда
    private Lead mapToEntity(LeadDTO dto) {
        Lead l =  new Lead();
        l.setName(dto.getName());
        l.setPhone(dto.getPhone());
        l.setTypeOfWork(dto.getTypeOfWork());
        l.setComment(dto.getComment());
        return l;
    }
    //Мапування ліда в дто
    private LeadDTO mapToDTO(Lead lead) {
        LeadDTO dto = new LeadDTO();
        dto.setId(lead.getId());
        dto.setName(lead.getName());
        dto.setPhone(lead.getPhone());
        dto.setTypeOfWork(lead.getTypeOfWork());
        dto.setComment(lead.getComment());
        return dto;
    }
}
