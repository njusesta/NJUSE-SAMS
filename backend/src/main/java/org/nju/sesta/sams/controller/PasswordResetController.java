package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.parameter.password.PasswordResetParameter;
import org.nju.sesta.sams.service.PasswordResetService;
import org.nju.sesta.sams.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/password")
public class PasswordResetController {

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private PasswordResetService service;

    @RequestMapping(value = "reset", method = RequestMethod.POST)
    public void resetPassword(@RequestParam PasswordResetParameter parameter, HttpServletRequest request){
        String authToken = request.getHeader("Authentication");
        final String token = authToken.substring(7);
        String username = jwtToken.getUsernameFromToken(token);
        service.resetPassword(parameter.getNewPassword(), username);
    }

}
