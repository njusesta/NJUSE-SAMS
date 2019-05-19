package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    EmailService service;


    /**
     * 请求发送邮件验证码接口
     *
     * @param param 包含用户账号和邮件种类(0代表注册邮件，1代表密码重置邮件)的字典
     * @return 处理结果
     */
    @RequestMapping(value = "/code_request",
            method = RequestMethod.POST)
    public ResponseEntity<?> sendCaptchaMail(@RequestBody Map<String, String> param) {
        service.sendVerificationCode(param.get("userId"), param.get("type"));
        return ResponseEntity.ok(null);
    }


}
