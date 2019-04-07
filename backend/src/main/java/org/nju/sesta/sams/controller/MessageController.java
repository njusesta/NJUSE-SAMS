package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.service.MessageService;
import org.nju.sesta.sams.util.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageService service;

    @Autowired
    JwtUtil jwtUtil;

    @Value("${jwt.header}")
    String tokenHeader;

    @RequestMapping(value = "/check",
            method = RequestMethod.GET)
    public ResponseEntity<?> checkMessagesUnread(HttpServletRequest request) {
        String studentId = jwtUtil.getUsernameFromRequest(request);
        Map<String, Boolean> res = new HashMap<>();
        res.put("haveMessageUnread", service.checkMessageUnread(studentId));
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET)
    public ResponseEntity<?> getMessageList(HttpServletRequest request) {
        String studentId = jwtUtil.getUsernameFromRequest(request);
        return ResponseEntity.ok(service.getMessageList(studentId));
    }

    @RequestMapping(value = "/{messageId}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getMessageDetail(@PathVariable Long messageId) {
        return ResponseEntity.ok(service.getMessageDetail(messageId));
    }

}
