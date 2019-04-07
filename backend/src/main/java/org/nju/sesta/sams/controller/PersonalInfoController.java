package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.entity.AuthUpRequest;
import org.nju.sesta.sams.enums.RoleName;
import org.nju.sesta.sams.exception.AuthorityException;
import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.parameter.PersonalInfo.AuthUpProcessParameter;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
import org.nju.sesta.sams.response.personalInfo.PersonalInfoResponse;
import org.nju.sesta.sams.service.PersonalInfoService;
import org.nju.sesta.sams.util.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/info")
public class PersonalInfoController {

    @Autowired
    PersonalInfoService service;

    @Autowired
    JwtUtil jwtUtil;

    @Value("${jwt.header}")
    String tokenHeader;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getPersonalInfo(@PathVariable String id, HttpServletRequest request) {
        if (!id.equals(jwtUtil.getUsernameFromRequest(request)))
            throw new AuthorityException();
        return ResponseEntity.ok(new PersonalInfoResponse(service.getPersonalInfo(id)));
    }

    @RequestMapping(value = "/",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonalInfo(@RequestBody BasicInfoParameter parameter, HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        service.updateBasicInfo(id, parameter);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/authority",
            method = RequestMethod.POST)
    public ResponseEntity<?> applyForAuthUpdating(Map<String, String> param, HttpServletRequest request) {
        String id = jwtUtil.getUsernameFromRequest(request);
        service.applyForAuthUpdating(RoleName.valueOf(param.get("targetAuthority")), id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/authority/requests",
            method = RequestMethod.GET)
    public ResponseEntity<?> getAuthUpRequests() {
        AuthUpRequest[] requests = service.getAuthUpRequests();
        return ResponseEntity.ok(requests);
    }

    @RequestMapping(value = "/authority",
            method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> processAuthUpRequest(@RequestBody AuthUpProcessParameter param) {
        service.processAuthUpRequest(param.getId(), param.getDecision());
        return ResponseEntity.ok(null);
    }

    @ExceptionHandler(BasicException.class)
    public ResponseEntity<?> handleBasicException(BasicException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
