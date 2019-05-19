package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.parameter.password.AccountSettingParameter;
import org.nju.sesta.sams.service.AccountService;
import org.nju.sesta.sams.util.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountService service;

    /**
     * 用户注册接口
     *
     * @param param 包含用户名、密码、验证码的字典
     * @return 处理结果
     */
    @RequestMapping(value = "/account/register",
            method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AccountSettingParameter param) {
        param.checkNotNull(
                () ->
                        new BasicException(HttpStatus.NOT_ACCEPTABLE, "格式不完整")
        );
        service.register(param.getId(), param.getPassword(), param.getCode());
        return ResponseEntity.ok(null);

    }

    /**
     * 用户重置密码接口
     *
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "/password/resetting",
            method = RequestMethod.PUT)
    public ResponseEntity<?> resetPassword(AccountSettingParameter param, HttpServletRequest request) {
        param.checkNotNullIgnoreId(
                () ->
                        new BasicException(HttpStatus.NOT_ACCEPTABLE, "格式不完整")
        );
        //id有两种取法，首先从token中取，对应用户自行修改密码；若失败则从param中取，对应忘记密码
        Optional<String> id = Optional.ofNullable(jwtUtil.getUsernameFromRequest(request));
        if (!id.isPresent())
            id = Optional.ofNullable(param.getId());
        service.resetPassword(id.orElseThrow(
                () ->
                        new BasicException(HttpStatus.NOT_ACCEPTABLE, "格式不完整")
        ), param.getPassword(), param.getCode());
        return ResponseEntity.ok(null);
    }

}
