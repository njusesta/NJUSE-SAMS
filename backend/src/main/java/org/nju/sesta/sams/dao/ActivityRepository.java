package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByOrderByInitDateDesc();
    Boolean existsActivityByname(String name);
}
