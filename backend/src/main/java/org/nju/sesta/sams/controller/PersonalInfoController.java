package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.parameter.PersonalInfo.ActivityInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.DevFormInfoParameter;
import org.nju.sesta.sams.response.personalInfo.PersonalInfoResponse;
import org.nju.sesta.sams.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "my")
public class PersonalInfoController {

    @Autowired
    PersonalInfoService service;

    @RequestMapping(value = "info/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getPersonalInfo(@PathVariable String id) {
        return ResponseEntity.ok(new PersonalInfoResponse(service.getPersonalInfo(id)));
    }

    //需要一个异常处理类
    @RequestMapping(value = "basic_info/{id}",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> updateBasicInfo(@PathVariable String id, @RequestBody BasicInfoParameter parameter){
        if(service.updateBasicInfo(id, parameter))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "activity_info/{id}",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> updateActivityInfo(@PathVariable String id, @RequestBody ActivityInfoParameter parameter){
        if(service.updateActivityInfo(id, parameter))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "dev_form_info/{id}",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> updateDevFormInfo(@PathVariable String id, @RequestBody DevFormInfoParameter parameter){
        if(service.updateDevAxFormInfo(id,parameter))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
