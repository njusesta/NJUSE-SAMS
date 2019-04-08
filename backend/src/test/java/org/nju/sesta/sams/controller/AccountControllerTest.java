package org.nju.sesta.sams.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nju.sesta.sams.parameter.password.AccountSettingParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

//    @Test
//    public void sendMailCaptchaTest() {
//        Map<String, String> param = new HashMap<>();
//        param.put("id", "171250530");
//        param.put("type", "1");
//        ResponseEntity<String> res = testRestTemplate.exchange("/email/code_request", HttpMethod.POST, new HttpEntity<>(param), String.class);
//        assertThat(res.getStatusCode()).as("无验证码检验").isEqualTo(HttpStatus.OK);
//    }

    @Test
    public void lackCodeTest() {//缺少验证码
        AccountSettingParameter param = new AccountSettingParameter();
        param.setId("171250530");
        param.setPassword("njuse2018");
        ResponseEntity<String> res = testRestTemplate.exchange("/account/resetting", HttpMethod.PUT, new HttpEntity<>(param), String.class);
        assertThat(res.getStatusCode()).as("无验证码检验").isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void lackPasswordTest() {//缺少密码
        AccountSettingParameter param = new AccountSettingParameter();
        param.setId("171250530");
        param.setCode("uiznec");
        ResponseEntity<String> res = testRestTemplate.exchange("/account/resetting", HttpMethod.PUT, new HttpEntity<>(param), String.class);
        assertThat(res.getStatusCode()).as("无密码检验").isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void lackTokenAndIdTest() {//缺少token和id
        AccountSettingParameter param = new AccountSettingParameter();
        param.setCode("uiznec");
        param.setPassword("njuse2018");
        ResponseEntity<String> res = testRestTemplate.exchange("/account/resetting", HttpMethod.PUT, new HttpEntity<>(param), String.class);
        assertThat(res.getStatusCode()).as("无id检验").isEqualTo(HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void normalTest() {
        AccountSettingParameter param = new AccountSettingParameter();
        param.setId("171250530");
        param.setPassword("njuse2017");
        param.setCode("uiznec");
        ResponseEntity<String> res = testRestTemplate.exchange("/account/resetting", HttpMethod.PUT, new HttpEntity<>(param), String.class);
        assertThat(res.getStatusCode()).as("正常检验").isEqualTo(HttpStatus.OK);
    }
}