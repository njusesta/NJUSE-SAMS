package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> findAllByActivityId(Long activityId);
}
