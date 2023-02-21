package com.taxes.calculator.infrastructure.utils;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class EntityMapper {
    private EntityMapper() {
	
    }
    
    public static <ENTITY,AGGR> Set<ENTITY> mapToEntity(final Set<AGGR> roles,Function<AGGR, ENTITY> mapper) {
   	return roles.stream().map(mapper)
   		.collect(Collectors.toSet());
       }
}
