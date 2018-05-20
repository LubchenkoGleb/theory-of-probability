package com.kpi.diploma.perevertailo.service.primary.impl;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Role;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.document.user.User;
import com.kpi.diploma.perevertailo.model.dto.GroupDto;
import com.kpi.diploma.perevertailo.model.pojo.EmailMessage;
import com.kpi.diploma.perevertailo.model.util.exception.IncorrectInputDataException;
import com.kpi.diploma.perevertailo.model.util.exception.ResourceNotFoundException;
import com.kpi.diploma.perevertailo.model.util.value.RoleValues;
import com.kpi.diploma.perevertailo.repository.GroupRepository;
import com.kpi.diploma.perevertailo.repository.RoleRepository;
import com.kpi.diploma.perevertailo.repository.UserRepository;
import com.kpi.diploma.perevertailo.service.primary.AdminService;
import com.kpi.diploma.perevertailo.service.util.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminsServiceImpl implements AdminService {

    private static final String message = "invites you to sign up on Theory of propability";

    private static final String subject = "Theory of propability";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final GroupRepository groupRepository;

    private final EmailService emailService;

    public AdminsServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                             GroupRepository groupRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.emailService = emailService;
    }

    @Override
    public List<String> inviteStudents(List<String> emails) {
        log.info("'inviteStudents' invoked with params'{}'", emails);

        List<String> needInvite = getNeedInviteEmails(emails);

        Role studentRole = roleRepository.findByRole(RoleValues.ROLE_STUDENT.toString());

        needInvite.forEach(email -> {

            Student student = new Student();
            student.getRoles().add(studentRole);
            student.setEmail(email);
            student.setInviteKey(UUID.randomUUID().toString());
            userRepository.save(student);

            String inviteUrl = "<br>http://localhost:8000/index.html?email=" +
                    email + "&inviteKey=" + student.getInviteKey();
            EmailMessage emailMessage = new EmailMessage(email, subject, message + inviteUrl);
            emailService.send(emailMessage);
        });

        return needInvite;
    }

    @Override
    public List<String> inviteTeachers(List<String> emails) {
        log.info("'inviteTeachers' invoked with params'{}'", emails);

        List<String> needInvite = getNeedInviteEmails(emails);

        Role teacherRole = roleRepository.findByRole(RoleValues.ROLE_TEACHER.toString());

        needInvite.forEach(email -> {

            Teacher teacher = new Teacher();
            teacher.getRoles().add(teacherRole);
            teacher.setEmail(email);
            teacher.setInviteKey(UUID.randomUUID().toString());
            userRepository.save(teacher);

            String inviteUrl = "<br>http://localhost:8000/index.html?email=" +
                    email + "&inviteKey=" + teacher.getInviteKey();
            EmailMessage emailMessage = new EmailMessage(email, subject, message + inviteUrl);
            emailService.send(emailMessage);
        });

        return needInvite;
    }

    private List<String> getNeedInviteEmails(List<String> emails) {

        List<User> allByEmail = userRepository.findAllByEmailIn(emails);
        log.info("'allByEmail={}'", allByEmail);

        List<String> existingEmails = allByEmail
                .stream()
                .map(User::getEmail)
                .collect(Collectors.toList());
        log.info("'existingEmails={}'");

        return emails
                .stream()
                .filter(email -> !existingEmails.contains(email))
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto addStudentToGroup(String studentId, String groupId) {
        log.info("'addStudentToGroup' invoked with params'{}, {}'", studentId, groupId);

        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        Optional<User> optionalStudent = userRepository.findById(studentId);

        if (!optionalGroup.isPresent() || !optionalStudent.isPresent()) {
            throw new ResourceNotFoundException("optionalStudent or optionalGroup not found");
        }

        Student student = (Student) optionalStudent.get();
        Group group = optionalGroup.get();

        student.setGroup(group);
        userRepository.save(student);

        group.getStudents().add(student);
        group = groupRepository.save(group);

        return new GroupDto(group, group.getStudents(), group.getTeacher());
    }

    @Override
    public GroupDto deleteStudentFromGroup(String studentId, String groupId) {
        log.info("'deleteStudentFromGroup' invoked with params'{}, {}'", studentId, groupId);

        Optional<User> optionalStudent = userRepository.findById(studentId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if (!optionalStudent.isPresent() || !optionalGroup.isPresent()) {
            throw new ResourceNotFoundException("student or group not found");
        }

        Student student = (Student) optionalStudent.get();
        Group group = optionalGroup.get();

        if (student.getGroup() == null || !student.getGroup().getId().equals(groupId)) {
            throw new IncorrectInputDataException("student isn't in group with id:" + groupId);
        }

        student.setGroup(null);
        userRepository.save(student);

        group.setStudents(group.getStudents()
                .stream().filter(st -> !st.getId().equals(studentId)).collect(Collectors.toList()));
        group = groupRepository.save(group);

        return new GroupDto(group, group.getStudents(), group.getTeacher());

    }

    @Override
    public GroupDto removeStudentsFromGroup(List<String> studentsIds, String groupId) {
        return null;
    }

    @Override
    public GroupDto setTeacherToGroup(String teacherId, String groupId) {
        log.info("'setTeacherToGroup' invoked with params'{}, {}'", teacherId, groupId);

        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        Optional<User> optionalTeacher = userRepository.findById(teacherId);

        if (!optionalGroup.isPresent() || !optionalTeacher.isPresent()) {
            throw new ResourceNotFoundException("teacher or group not found");
        }

        Teacher teacher = (Teacher) optionalTeacher.get();
        Group group = optionalGroup.get();

        teacher.getGroups().add(group);
        userRepository.save(teacher);

        group.setTeacher(teacher);
        group = groupRepository.save(group);

        return new GroupDto(group, group.getStudents(), group.getTeacher());
    }
}
