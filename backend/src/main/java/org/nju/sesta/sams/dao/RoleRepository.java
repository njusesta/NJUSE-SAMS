package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Role;
import org.nju.sesta.sams.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleName(RoleName roleName);

    Boolean existsRoleByRoleName(RoleName roleName);

}
