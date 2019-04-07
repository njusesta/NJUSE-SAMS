package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.parameter.password.PasswordResetParameter;
import org.nju.sesta.sams.service.PasswordService;
import org.nju.sesta.sams.util.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(value = "/password")
public class PasswordController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordService service;

    @RequestMapping(value = "/resetting", method = RequestMethod.PUT)
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetParameter param, HttpServletRequest request) {
        Optional<String> id = Optional.ofNullable(jwtUtil.getUsernameFromRequest(request));
        service.resetPassword(param.getNewPassword(), param.getCode(), id.orElse(param.getId()));
        return ResponseEntity.ok(null);
    }

}
