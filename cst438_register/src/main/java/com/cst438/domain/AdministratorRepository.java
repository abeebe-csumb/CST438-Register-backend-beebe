package com.cst438.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdministratorRepository extends CrudRepository <Administrator, Integer>{

	@Query("select a from Administrator a where a.email=:email")
	public Administrator findByEmail(@Param("email") String email);
}
