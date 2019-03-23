package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.MailCaptcha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailCaptchaRepository extends JpaRepository<MailCaptcha, Long> {
    List<MailCaptcha> findByEmailAddress(String emailAddress);
}
