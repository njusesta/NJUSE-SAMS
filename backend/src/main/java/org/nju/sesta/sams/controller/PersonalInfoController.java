package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.parameter.PersonalInfo.ActivityInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.DevFormInfoParameter;
import org.nju.sesta.sams.response.personalInfo.PersonalInfoResponse;
import org.nju.sesta.sams.service.PersonalInfoService;
import org.nju.sesta.sams.util.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/info")
public class PersonalInfoController {

    @Autowired
    PersonalInfoService service;

    @Autowired
    JwtToken jwtToken;

    @Value("${jwt.header}")
    String tokenHeader;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getPersonalInfo(@PathVariable String id, HttpServletRequest request) {
        return ResponseEntity.ok(new PersonalInfoResponse(service.getPersonalInfo(id)));
    }

    //需要一个异常处理类
    @RequestMapping(value = "/",
            method = RequestMethod.PUT,
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> updatePersonalInfo(@RequestBody BasicInfoParameter parameter, HttpServletRequest request) {
        String id = getIdFromRequest(request);
        if (service.updateBasicInfo(id, parameter))
            return ResponseEntity.ok(null);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "activity_info/{id}",
//            method = RequestMethod.POST,
//            produces = {"application/json", "application/xml"})
//    public ResponseEntity<?> updateActivityInfo(@PathVariable String id, @RequestBody ActivityInfoParameter parameter) {
//        if (service.updateActivityInfo(id, parameter))
//            return ResponseEntity.ok(null);
//        else
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }
//
//    @RequestMapping(value = "dev_form_info/{id}",
//            method = RequestMethod.POST,
//            produces = {"application/json", "application/xml"})
//    public ResponseEntity<?> updateDevFormInfo(@PathVariable String id, @RequestBody DevFormInfoParameter parameter) {
//        if (service.updateDevAxFormInfo(id, parameter))
//            return ResponseEntity.ok(null);
//        else
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }

    @RequestMapping(value = "/authority", method = RequestMethod.POST)
    public ResponseEntity<?> applyForAuthorityUpdating(Map<String, String> param, HttpServletRequest request) {
        if (param.containsKey("targetAuthority")) {
            String id = getIdFromRequest(request);
            if (service.applyForAuthorityUpdating(param.get("targetAuthority"), id))
                return ResponseEntity.ok(null);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private String getIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return jwtToken.getUsernameFromToken(token);
    }

}
