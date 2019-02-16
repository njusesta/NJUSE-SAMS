package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.entity.Message;
import org.nju.sesta.sams.service.MessageService;
import org.nju.sesta.sams.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageService service;

    @Autowired
    JwtToken jwtToken;

    @Value("${tokenHeader}")
    String header;

    @RequestMapping(value = "/check",
            method = RequestMethod.GET)
    public Map<String, Boolean> checkMessagesUnead(HttpServletRequest request){
        String studentId = getUserIdFromRequest(request);
        Map<String, Boolean> res = new HashMap<>() ;
        res.put("haveMessageUnread", service.checkMessageUnread(studentId));
        return res;
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET)
    public List<Message> getMessageList(HttpServletRequest request){
        String studentId = getUserIdFromRequest(request);
        return service.getMessageList(studentId);
    }

    @RequestMapping(value = "/{messageId}",
            method = RequestMethod.GET)
    public Message  getMessageDetail(@PathVariable Long messageId){
        return service.getMessageDetail(messageId);
    }


    private String getUserIdFromRequest(HttpServletRequest request){
        String token = request.getHeader(header).substring(7);
        return jwtToken.getUsernameFromToken(token);
    }
}
