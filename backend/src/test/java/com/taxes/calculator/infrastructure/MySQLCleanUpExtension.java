package com.taxes.calculator.infrastructure;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.taxes.calculator.infrastructure.fixedtax.persistence.FixedTaxRepository;
import com.taxes.calculator.infrastructure.hourvalue.persistence.HourValueRepository;
import com.taxes.calculator.infrastructure.role.persistence.RoleRepository;
import com.taxes.calculator.infrastructure.totaltax.persistence.TotalTaxRepository;
import com.taxes.calculator.infrastructure.user.persistence.UserRepository;
import com.taxes.calculator.infrastructure.variabletax.persistence.VariableTaxRepository;

public class MySQLCleanUpExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(final ExtensionContext context)
	    throws Exception {

	final var repositories = SpringExtension
		.getApplicationContext(context)
		.getBeansOfType(CrudRepository.class).values();

	cleanUp(repositories);
	final var appContext = SpringExtension
		.getApplicationContext(context);

	cleanUp(List.of(appContext.getBean(FixedTaxRepository.class),
		appContext.getBean(VariableTaxRepository.class),
		appContext.getBean(HourValueRepository.class),
		appContext.getBean(RoleRepository.class),
		appContext.getBean(UserRepository.class),
		appContext.getBean(HourValueRepository.class),
		appContext.getBean(TotalTaxRepository.class)));

    }

    private void cleanUp(Collection<CrudRepository> repositories) {
	repositories.forEach(CrudRepository::deleteAll);
    }
}