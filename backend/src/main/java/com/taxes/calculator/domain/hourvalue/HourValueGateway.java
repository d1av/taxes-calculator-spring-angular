package com.taxes.calculator.domain.hourvalue;

import java.util.List;
import java.util.Optional;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public interface HourValueGateway {

    // create
    HourValue create(HourValue aHourValue);

    // read
    Optional<HourValue> findById(HourValueID anId);

    Pagination<HourValue> findAll(SearchQuery aQuery);

    // update
    HourValue update(HourValue aHourValue);

    // delete
    void deleteById(HourValueID anId);

    // utils
    List<HourValueID> existsByIds(Iterable<HourValueID> ids);
}
