package com.example.backand.Repository;

import com.example.backand.DTO.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead,Long> {
}
