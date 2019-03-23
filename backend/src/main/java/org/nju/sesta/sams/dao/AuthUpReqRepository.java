package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.AuthUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthUpReqRepository extends JpaRepository<AuthUpRequest, Long> {
    List<AuthUpRequest> findByStudentId(String studentId);
}
