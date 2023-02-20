package com.taxes.calculator.infrastructure.role.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taxes.calculator.domain.role.Role;

public interface RoleRepository
	extends JpaRepository<RoleJpaEntity, String> {

    Optional<RoleJpaEntity> findByAuthority(String authority);

    @Query(value = "select r.id from Role r where r.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
