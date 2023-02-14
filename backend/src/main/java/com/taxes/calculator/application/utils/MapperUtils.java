package com.taxes.calculator.application.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class MapperUtils {

    public static <T, R> List<R> toID(Collection<T> anCollection,
	    Function<T, R> mapper) {
	return anCollection.stream().map(mapper).toList();
    }

}
