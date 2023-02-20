package com.taxes.calculator.infrastructure.role.persistence;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository
	extends JpaRepository<RoleJpaEntity, String> {
    
    Page<RoleJpaEntity> findAll(Specification<RoleJpaEntity> whereClause, Pageable pageable);

    Optional<RoleJpaEntity> findByAuthority(String authority);

    @Query(value = "select r.id from Role r where r.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
