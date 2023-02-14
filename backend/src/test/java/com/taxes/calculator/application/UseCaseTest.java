package com.taxes.calculator.application;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxes.calculator.domain.Identifier;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public abstract class UseCaseTest implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context)
	    throws Exception {
	Mockito.reset(getMocks().toArray());

    }

    protected abstract List<Object> getMocks();

    protected Set<String> asString(
	    final Collection<? extends Identifier> ids) {
	return ids.stream().map(Identifier::getValue)
		.collect(Collectors.toSet());
    }
}
