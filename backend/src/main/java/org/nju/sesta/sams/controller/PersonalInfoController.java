package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.exception.AuthorityException;
import org.nju.sesta.sams.exception.BasicException;
import org.nju.sesta.sams.parameter.PersonalInfo.BasicInfoParameter;
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
            method = RequestMethod.GET)
    public ResponseEntity<?> getPersonalInfo(@PathVariable String id, HttpServletRequest request) {
        if (!id.equals(jwtToken.getUsernameFromRequest(request)))
            throw new AuthorityException();
        return ResponseEntity.ok(new PersonalInfoResponse(service.getPersonalInfo(id)));
    }

    @RequestMapping(value = "/",
            method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonalInfo(@RequestBody BasicInfoParameter parameter, HttpServletRequest request) {
        String id = jwtToken.getUsernameFromRequest(request);
        service.updateBasicInfo(id, parameter);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/authority", method = RequestMethod.POST)
    public ResponseEntity<?> applyForAuthorityUpdating(Map<String, String> param, HttpServletRequest request) {
        String id = jwtToken.getUsernameFromRequest(request);
        service.applyForAuthorityUpdating(param.get("targetAuthority"), id);
        return ResponseEntity.ok(null);
    }

    @ExceptionHandler(BasicException.class)
    public ResponseEntity<?> handleBasicException(BasicException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
