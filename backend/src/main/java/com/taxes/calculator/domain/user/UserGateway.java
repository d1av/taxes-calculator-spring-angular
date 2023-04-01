package com.taxes.calculator.domain.user;

import java.util.List;
import java.util.Optional;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;

public interface UserGateway {

    // create
    User create(User aUser);

    // read
    Optional<User> findById(String anId);

    
    Optional<User> findByName(String name);

    Pagination<User> findAll(SearchQuery aQuery);

    // update
    User update(User aUser);

    // delete
    void deleteById(UserID anId);

    // utils
    List<UserID> existsByIds(Iterable<UserID> ids);

}
