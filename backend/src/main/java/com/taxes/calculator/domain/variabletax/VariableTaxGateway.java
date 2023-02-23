package com.taxes.calculator.domain.variabletax;

import java.util.List;
import java.util.Optional;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public interface VariableTaxGateway {

    // create
    VariableTax create(VariableTax aVariableTax);

    // read
    Optional<VariableTax> findById(VariableTaxID anId);

    Pagination<VariableTax> findAll(SearchQuery aQuery);

    // update
    VariableTax update(VariableTax aVariableTax);

    // delete
    void deleteById(VariableTaxID anId);
}
