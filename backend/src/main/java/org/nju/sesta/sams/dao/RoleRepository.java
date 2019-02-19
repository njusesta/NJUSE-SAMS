package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
