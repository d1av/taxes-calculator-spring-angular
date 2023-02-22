package com.taxes.calculator.infrastructure.utils;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationUtils {
    private SpecificationUtils() {
    }
    
    public static <T> Specification<T> assembleSpecification(
	    final String str) {
	final Specification<T> nameLike = SpecificationUtils.like("id",
		str);
	return nameLike.or(SpecificationUtils.like("userId", str));
    }

    public static <T> Specification<T> like(final String prop, final String term) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get(prop)), SqlUtils.like(term.toUpperCase()));
    }
}