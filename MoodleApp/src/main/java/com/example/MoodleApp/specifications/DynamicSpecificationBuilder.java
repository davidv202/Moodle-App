package com.example.MoodleApp.specifications;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DynamicSpecificationBuilder<T> {

    public Specification<T> buildSpecification(Map<String, Object> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                if (value != null) {
                    if(value instanceof String) {
                        predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
                    }
                    else {
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                    }
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
