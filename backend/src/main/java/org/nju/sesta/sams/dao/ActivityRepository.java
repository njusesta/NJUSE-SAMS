package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
