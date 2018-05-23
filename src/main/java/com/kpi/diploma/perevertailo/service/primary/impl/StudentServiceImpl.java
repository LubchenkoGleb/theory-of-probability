package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.dto.StudentDto;
import com.kpi.diploma.perevertailo.repository.StudentRepository;
import com.kpi.diploma.perevertailo.service.primary.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getByGroup(String groupId) {
        log.info("'getListByGroup' invoked with params'{}'", groupId);

        List<Student> allByGroupId = studentRepository.getAllByGroupId(groupId);
        log.info("'allByGroupId={}'", allByGroupId);

        return allByGroupId;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        log.info("'getAll' invoked");

        List<Student> all = studentRepository.findAll();

        List<StudentDto> allDtos = all
                .stream()
                .map(student -> new StudentDto(student, student.getGroup()))
                .collect(Collectors.toList());

        return allDtos;
    }

    @Override
    public List<Student> getStudentsWithoutGroup() {

        return studentRepository.findAllByGroupAndEnable(null, true);
    }


}
