package org.nju.sesta.sams.controller;

import org.nju.sesta.sams.parameter.activity.NewMatchParameter;
import org.nju.sesta.sams.service.ActivityManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activity")
public class ActivityManageController {
    @Autowired
    ActivityManageService service;

    @RequestMapping(value = "/match/new",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> applyForNewActivity(@RequestParam NewMatchParameter parameter){
        if(service.applyForNewMatch(parameter))
            return ResponseEntity.ok(null);
        else 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
