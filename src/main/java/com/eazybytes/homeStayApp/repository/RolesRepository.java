package com.eazybytes.homeStayApp.repository;

import com.eazybytes.homeStayApp.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    public Roles getByRoleName(String roleName);
}
