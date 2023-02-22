package com.taxes.calculator.domain.fixedtax;

import java.util.Optional;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public interface FixedTaxGateway {

    // create
    FixedTax create(FixedTax aFixedTax);

    // read
    Optional<FixedTax> findById(FixedTaxID anId);

    Pagination<FixedTax> findAll(SearchQuery aQuery);

    // update
    FixedTax update(FixedTax aFixedTax);

    // delete
    void deleteById(FixedTaxID anId);
}
