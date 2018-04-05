package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Journal;
import com.kpi.diploma.perevertailo.model.document.Task;
import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.service.primary.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Override
    public List<Group> getListOfGroup(String teacherId) {
        return null;
    }

    @Override
    public Task createTask(String theme, String question, String answer, String teacherId) {
        return null;
    }

    @Override
    public List<Task> getTasks(String teacherId) {
        return null;
    }

    @Override
    public Test createTest(Test test, String teacherId) {
        return null;
    }

    @Override
    public Test editTest(Test test, String teacherId) {
        return null;
    }

    @Override
    public Test deleteTest(Test test, String teacherId) {
        return null;
    }

    @Override
    public List<Test> getTestByTheme(String theme, String teacherId) {
        return null;
    }

    @Override
    public void assignTestToGroup(String testId, String groupId, String teacherId) {

    }

    @Override
    public Journal getJournal(String groupId, String teacherId) {
        return null;
    }

//    @Override
//    public ManagerDto registerManager(RegistrationManagerDto registrationManagerDto) {
//        log.info("'registerManager' invoked with params'{}'", registrationManagerDto);
//
//        Manager managerEntity = managerRepository.findByInviteKey(registrationManagerDto.getInviteKey());
//
//        if (managerEntity == null || !managerEntity.getEmail().equals(registrationManagerDto.getEmail())) {
//            String errMsg = "inviteKey not found or doesn't belong to email";
//            log.error(errMsg);
//            throw new IncorrectInviteKey(errMsg);
//        }
//
//        managerEntity.setEnable(true);
//        managerEntity.setPassword(passwordEncoder.encode(registrationManagerDto.getPassword()));
//        managerEntity.setFirstName(registrationManagerDto.getFirstName());
//        managerEntity.setLastName(registrationManagerDto.getLastName());
//        managerEntity = managerRepository.save(managerEntity);
//        log.info("'managerEntity={}'", managerEntity);
//
//        ManagerDto managerDto = ManagerDto.convert(managerEntity);
//        managerDto.setAccessToken(tokenService.getToken(managerDto.getEmail()));
//        return managerDto;
//    }
}
