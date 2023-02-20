package com.taxes.calculator.domain.role;

import java.util.Optional;
import java.util.Set;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public interface RoleGateway {

    // create
    Role create(Role aRole);

    // read
    Optional<Role> findById(RoleID anId);

    Pagination<Role> findAll(SearchQuery aQuery);

    // update
    Role update(Role aRole);

    // delete
    void deleteById(RoleID anId);

    // utils
    Set<RoleID> existsByIds(Iterable<RoleID> roledIds);
    
    Boolean existById(String id);

    Optional<Role> findByAuthority(String anAuthority);
}
