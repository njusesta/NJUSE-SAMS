package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    int saveActivity(Activity activity);
}
