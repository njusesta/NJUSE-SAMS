package org.nju.sesta.sams.dao;

import org.nju.sesta.sams.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findById(long id);

    List<Message> findByStudentId(String studentId);

    List<Message> findByStudentIdAndIsRead(String studentId, boolean isRead);
}
