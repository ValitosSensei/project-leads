package com.example.backand.Repository;

import com.example.backand.DTO.Lead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LeadRepository extends JpaRepository<Lead,Long> , JpaSpecificationExecutor<Lead> {
    Page<Lead> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Lead> findByTypeOfWorkContainingIgnoreCase(String typeOfWork, Pageable pageable);

    Page<Lead> findByPhoneContaining(String phone, Pageable pageable);

    Page<Lead>  findByNameContainingIgnoreCaseAndTypeOfWorkContainingIgnoreCase(String name,String typeOfWork, Pageable pageable);


}
