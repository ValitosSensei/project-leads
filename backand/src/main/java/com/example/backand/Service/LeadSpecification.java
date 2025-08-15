package com.example.backand.Service;

import com.example.backand.DTO.Lead;
import org.springframework.data.jpa.domain.Specification;

public class LeadSpecification {

    public static Specification<Lead> hasName(String name) {
        return (root, query, cb) ->
                name == null || name.isEmpty() ? null :
                        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
    public static Specification<Lead> hasWorkType(String workType) {
        return (root, query, cb) ->
                workType == null || workType.isEmpty() ? null :
                        cb.like(cb.lower(root.get("name")), workType);
    }
    public static Specification<Lead> hasCallStatus(String callStatus) {
        return (root, query, cb) ->
                callStatus == null || callStatus.isEmpty() ? null :
                        cb.equal(root.get("callStatus"), callStatus);
    }
}
