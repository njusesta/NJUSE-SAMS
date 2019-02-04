package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.parameter.password.PasswordResetParameter;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/password")
public class PasswordRqestController {



    @RequestMapping(value = "reset", method = RequestMethod.POST)
    public void resetPassword(@RequestParam PasswordResetParameter parameter, HttpServletRequest request){
        //待实现
    }

}
