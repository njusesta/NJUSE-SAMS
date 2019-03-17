package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class MailController {

    @Autowired
    MailService service;

    @RequestMapping(value = "/initialization",
            method = RequestMethod.POST)
    public ResponseEntity<?> initAccount(@RequestBody Map<String, String> param) {
        if (param.containsKey("id")) {
            try {
                service.initAccount(param.get("id"));
                return ResponseEntity.ok(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
