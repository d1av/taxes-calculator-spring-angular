package com.taxes.calculator.application.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperUtils {

    public static <T, R> Set<R> toID(Collection<T> anCollection,
	    Function<T, R> mapper) {
	return anCollection.stream().map(mapper)
		.collect(Collectors.toSet());
    }

}
