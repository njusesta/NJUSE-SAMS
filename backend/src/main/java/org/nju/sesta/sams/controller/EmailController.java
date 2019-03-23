package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.exception.BadFormatException;
import org.nju.sesta.sams.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    EmailService service;

    @RequestMapping(value = "/initialization",
            method = RequestMethod.POST)
    public ResponseEntity<?> initAccount(@RequestBody Map<String, String> param) {
        Optional<String> id = Optional.ofNullable(param.get("studentId"));
        service.initAccount(id.orElseThrow(BadFormatException::new));
        return ResponseEntity.ok(null);

    }

    @RequestMapping(value = "/code_request",
            method = RequestMethod.POST)
    public ResponseEntity<?> sendCaptchaMail(@RequestBody Map<String, String> param) {
        Optional<String> id = Optional.ofNullable(param.get("studentId"));
        service.sendVerificationCode(id.orElseThrow(BadFormatException::new));
        return ResponseEntity.ok(null);
    }

}
