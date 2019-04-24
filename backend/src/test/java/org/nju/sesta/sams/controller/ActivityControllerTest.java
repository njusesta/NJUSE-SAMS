package org.nju.sesta.sams.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nju.sesta.sams.parameter.activity.NewMatchParameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void NewMatchNormal() throws ParseException {
        NewMatchParameter param = new NewMatchParameter();
        param.setContent("A new small match");
        param.setLimitedNumber(5);
        param.setName("njuse2018");
        String str="2018-05-11";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String string="2019-5-29";
        SimpleDateFormat sdfs= new SimpleDateFormat("yyyy-MM-dd");
        Date dat1 =sdfs.parse(string);
        Calendar calendar1 = Calendar.getInstance();
        param.setRegEndDate(calendar1);
        param.setRegStartDate(calendar);
        param.setWay2Register("ddd");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzEyNTA3MDIiLCJleHAiOjE1NTY0MzQ1NDUsImlhdCI6MTU1NTgyOTc0NX0.2JMR_IyT2LrUMIFlRLCHL1h1JIDmdX8kAZtoAor-ZEmgmq2fRM8O9f1DbEdAQDQcmg4etz_2OugDZorvNplUcA");
        ResponseEntity<String> res = testRestTemplate.exchange("/activity/match/new", HttpMethod.POST, new HttpEntity<>(param,headers), String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void NewActivityNormal() throws ParseException {
        NewMatchParameter param = new NewMatchParameter();
        param.setContent("A new small match");
        param.setLimitedNumber(5);
        param.setName("njus2018");
        String str="2018-05-27";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String string="2019-5-27";
        SimpleDateFormat sdfs= new SimpleDateFormat("yyyy-MM-dd");
        Date dat1 =sdfs.parse(string);
        Calendar calendar1 = Calendar.getInstance();
        param.setRegEndDate(calendar1);
        param.setRegStartDate(calendar);
        param.setWay2Register("ddd");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzEyNTA3MDIiLCJleHAiOjE1NTY0MzQ1NDUsImlhdCI6MTU1NTgyOTc0NX0.2JMR_IyT2LrUMIFlRLCHL1h1JIDmdX8kAZtoAor-ZEmgmq2fRM8O9f1DbEdAQDQcmg4etz_2OugDZorvNplUcA");
        ResponseEntity<String> res = testRestTemplate.exchange("/activity/activity/new", HttpMethod.POST, new HttpEntity<>(param,headers), String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void NewRecruitNormal(){

    }
}
