package com.example.backand.Service;

import com.example.backand.DTO.*;
import com.example.backand.Repository.LeadRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


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

    @Override
    public Page<LeadDTO> searchByName(String name, Pageable pageable) {
        return leadRepository.findByNameContainingIgnoreCase(name,pageable)
                .map(this::mapToDTO);
    }

    @Override
    public Page<LeadDTO> searchByType(String typeOfWork, Pageable pageable) {
        return leadRepository.findByTypeOfWorkContainingIgnoreCase(typeOfWork,pageable)
                .map(this::mapToDTO);
    }

    @Override
    public Page<LeadDTO> searchByPhone(String phone, Pageable pageable) {
        return  leadRepository.findByPhoneContaining(phone,pageable).map(this::mapToDTO);
    }

    public Page<LeadDTO> searchByNameAndTypeOfWork(String name,String typeOfWork ,Pageable pageable) {
    return leadRepository
            .findByNameContainingIgnoreCaseAndTypeOfWorkContainingIgnoreCase(name,typeOfWork,pageable).map(this::mapToDTO);
    }

    public  LeadDTO updateStatusAndComment(Long id, LeadStatus status,String adminComment) {
        Lead lead = leadRepository.findById(id).orElseThrow(()-> new RuntimeException("Lead not founded"));
        lead.setStatus(status);
        lead.setAdminComment(adminComment);

        return mapToDTO(leadRepository.save(lead));
    }

    public Page<LeadDTO> searchLead(LeadSearchRequestDTO searchRequestDTO, Pageable pageable) {
        Specification<Lead> spec = null;

        if (searchRequestDTO.getName() != null && !searchRequestDTO.getName().isBlank()) {
            spec = combine(spec, LeadSpecification.hasName(searchRequestDTO.getName()));
        }
        if (searchRequestDTO.getTypeOfWork() != null && !searchRequestDTO.getTypeOfWork().isBlank()) {
            spec = combine(spec, LeadSpecification.hasWorkType(searchRequestDTO.getTypeOfWork()));
        }
        if (searchRequestDTO.getCallStatus() != null&& !searchRequestDTO.getCallStatus().isBlank()) {
            spec = combine(spec, LeadSpecification.hasCallStatus(searchRequestDTO.getCallStatus()));
        }

        Sort sort = Sort.by(Sort.Direction.fromString(searchRequestDTO.getSortDirection()), searchRequestDTO.getSortBy());
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return leadRepository.findAll(spec, sortedPageable)
                .map(this::mapToDTO);
    }

    // Допоміжний метод для комбінування Specification
    private Specification<Lead> combine(Specification<Lead> spec1, Specification<Lead> spec2) {
        if (spec1 == null) return spec2;
        return spec1.and(spec2);
    }

    //Мапування дто в ліда
    private Lead mapToEntity(LeadDTO dto) {
        Lead l = new Lead();
        l.setName(dto.getName());
        l.setPhone(dto.getPhone());
        l.setTypeOfWork(dto.getTypeOfWork());
        l.setComment(dto.getComment());

        if(dto.getStatus() != null){
            l.setStatus(dto.getStatus());
        }

        // 🔹 Перетворюємо рядки з DTO у Enum ContactMethod
        if(dto.getContactMethods() != null) {
            Set<ContactMethod> methods = dto.getContactMethods().stream()
                    .map(ContactMethod::valueOf) // "PHONE" -> ContactMethod.PHONE
                    .collect(Collectors.toSet());
            l.setContactMethods(methods);
        }

        l.setAdminComment(dto.getAdminComment());
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
        dto.setCreatedAt(lead.getCreatedAt());
        dto.setStatus(lead.getStatus());
        if(lead.getContactMethods() != null) {
            Set<String> methods = lead.getContactMethods()
                    .stream()
                    .map(Enum::name) // ContactMethod.PHONE -> "PHONE"
                    .collect(Collectors.toSet());
            dto.setContactMethods(methods);
        }

        dto.setAdminComment(lead.getAdminComment());
        return dto;
    }
}
