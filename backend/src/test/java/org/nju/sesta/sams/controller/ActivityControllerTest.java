package org.nju.sesta.sams.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nju.sesta.sams.dao.UserRepository;
import org.nju.sesta.sams.entity.Activity;
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
    @Autowired
    UserRepository userRepository ;
    @Test
    public void NewMatchNormal() throws ParseException {
        NewMatchParameter param = new NewMatchParameter();
        param.setContent("一个撒旦骄傲的将军");
        param.setLimitedNumber(5);
        param.setName("2019");
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
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzEyNTA3MDIiLCJleHAiOjE1NTcwNjc3NDQsImlhdCI6MTU1NjQ2Mjk0NH0.MBxjGeFBCszHpoFny61Vsi50dRmTKj4-3hktpnFFdoSV7vHsFW96rqN7smO2TJyNxHiFhhkq5NlO8zlBVmcxMg");
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
    @Test
    public void trueOwner() throws ParseException{//正常更新
        Activity param = new Activity();
        param.setContent("A new small match");
        param.setLimited(5);
        param.setName("足球赛");
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
        param.setCreator(userRepository.findById("171250702").get());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzEyNTA3MDIiLCJleHAiOjE1NTcwNjc3NDQsImlhdCI6MTU1NjQ2Mjk0NH0.MBxjGeFBCszHpoFny61Vsi50dRmTKj4-3hktpnFFdoSV7vHsFW96rqN7smO2TJyNxHiFhhkq5NlO8zlBVmcxMg");
        ResponseEntity<String> res = testRestTemplate.exchange("/activity/activity", HttpMethod.PUT, new HttpEntity<>(param,headers), String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    public void ActivityUpdateWongl() throws ParseException {//更新者不是真正主人
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
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzEyNTA3MDIiLCJleHAiOjE1NTcwNjc3NDQsImlhdCI6MTU1NjQ2Mjk0NH0.MBxjGeFBCszHpoFny61Vsi50dRmTKj4-3hktpnFFdoSV7vHsFW96rqN7smO2TJyNxHiFhhkq5NlO8zlBVmcxMg");
        ResponseEntity<String> res = testRestTemplate.exchange("/activity/activity", HttpMethod.PUT, new HttpEntity<>(param,headers), String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public  void getListTest() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzEyNTA3MDIiLCJleHAiOjE1NTcwNjc3NDQsImlhdCI6MTU1NjQ2Mjk0NH0.MBxjGeFBCszHpoFny61Vsi50dRmTKj4-3hktpnFFdoSV7vHsFW96rqN7smO2TJyNxHiFhhkq5NlO8zlBVmcxMg");
        ResponseEntity<String> res = testRestTemplate.exchange("/activity/list", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public  void getUnexaimedListTest1() throws Exception{//不具有管理员权限
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNzEyNTA3MDIiLCJleHAiOjE1NTcwNjc3NDQsImlhdCI6MTU1NjQ2Mjk0NH0.MBxjGeFBCszHpoFny61Vsi50dRmTKj4-3hktpnFFdoSV7vHsFW96rqN7smO2TJyNxHiFhhkq5NlO8zlBVmcxMg");
        ResponseEntity<String> res = testRestTemplate.exchange("/activity/unexamined", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
