package org.nju.sesta.sams.service;

import org.nju.sesta.sams.dao.MessageRepository;
import org.nju.sesta.sams.entity.Message;
import org.nju.sesta.sams.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;

    public boolean checkMessageUnread(String studentId) {
        List<Message> res = repository.findByStudentIdAndIsRead(studentId, false);
        return res != null && res.size() != 0;
    }

    public List<Message> getMessageList(String studentId) {
        return repository.findByStudentId(studentId);
    }

    public Message getMessageDetail(Long id) {
        return repository.findById(id).orElseThrow(SystemException::new);
    }
}


