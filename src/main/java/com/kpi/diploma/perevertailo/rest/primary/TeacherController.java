package com.kpi.diploma.perevertailo.rest.primary;

import com.kpi.diploma.perevertailo.service.primary.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/manager-requests")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService driverService) {
        this.teacherService = driverService;
    }

//    @PostMapping(value = "/confirm")
//    private ResponseEntity<ManagerDto> confirmProfile(@RequestBody RegistrationManagerDto registrationManagerDto) {
//        log.info("'confirmProfile' invoked with params'{}'", registrationManagerDto);
//
//        ManagerDto managerDto = teacherService.registerManager(registrationManagerDto);
//        log.info("'managerDto={}'", managerDto);
//
//        return ResponseEntity.ok(managerDto);
//    }
}
