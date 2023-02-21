package com.taxes.calculator.infrastructure.user.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taxes.calculator.domain.pagination.Pagination;
import com.taxes.calculator.domain.pagination.SearchQuery;
import com.taxes.calculator.domain.user.User;

public interface UserRepository
	extends JpaRepository<UserJpaEntity, String> {

    Page<UserJpaEntity> findAll(
	    Specification<UserJpaEntity> whereClause,
	    Pageable pageable);

    Optional<UserJpaEntity> findByName(String name);

    @Query(value = "select r.id from User r where r.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
