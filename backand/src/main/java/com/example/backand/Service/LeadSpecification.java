package com.example.backand.Service;

import com.example.backand.DTO.Lead;
import com.example.backand.DTO.LeadStatus;
import org.springframework.data.jpa.domain.Specification;

public class LeadSpecification {

    public static Specification<Lead> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Lead> hasWorkType(String typeOfWork) {
        return (root, query, cb) -> {
            if (typeOfWork == null || typeOfWork.isBlank()) return cb.conjunction();
            return cb.like(cb.lower(root.get("typeOfWork")), "%" + typeOfWork.toLowerCase() + "%");
        };
    }

    public static Specification<Lead> hasCallStatus(String callStatus) {
        return (root, query, cb) -> {
            if (callStatus == null || callStatus.isBlank()) return cb.conjunction();
            try {
                LeadStatus status = LeadStatus.valueOf(callStatus.toUpperCase());
                return cb.equal(root.get("status"), status); // тут enum порівнюємо через cb.equal
            } catch (IllegalArgumentException e) {
                return cb.conjunction(); // якщо неправильний enum, повертаємо всі записи
            }
        };
    }
}
