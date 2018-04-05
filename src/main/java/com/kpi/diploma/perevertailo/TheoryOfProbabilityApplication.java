package com.kpi.diploma.perevertailo;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Role;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.document.user.User;
import com.kpi.diploma.perevertailo.model.util.value.RoleValues;
import com.kpi.diploma.perevertailo.repository.GroupRepository;
import com.kpi.diploma.perevertailo.repository.RoleRepository;
import com.kpi.diploma.perevertailo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class TheoryOfProbabilityApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final GroupRepository groupRepository;

    private final PasswordEncoder passwordEncoder;

    private User admin;

    private Group group;

    private Teacher teacher;

    private List<Student> students;

    @Autowired
    public TheoryOfProbabilityApplication(UserRepository userRepository, RoleRepository roleRepository,
                                          GroupRepository groupRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(TheoryOfProbabilityApplication.class, args);
    }

    @Override
    public void run(String... strings) {

        roleRepository.deleteAll();

        userRepository.deleteAll();

        groupRepository.deleteAll();

        initRoles();

        createAdmin();

        createStudents();

        createTeacher();

        createGroup();

        usersForConfirmation();

    }

    private void initRoles() {

        Role adminRole = new Role(RoleValues.ROLE_ADMIN.toString());
        Role teacherRole = new Role(RoleValues.ROLE_TEACHER.toString());
        Role studentRole = new Role(RoleValues.ROLE_STUDENT.toString());
        List<Role> roles = Arrays.asList(adminRole, teacherRole, studentRole);

        roleRepository.saveAll(roles);

    }

    private void createAdmin() {

        Role adminRole = roleRepository.findByRole(RoleValues.ROLE_ADMIN.toString());

        admin = new User();
        admin.setEmail("admin");
        admin.setEnable(true);
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));

        admin = userRepository.save(admin);

    }

    private void createStudents() {

        Role studentRole = roleRepository.findByRole(RoleValues.ROLE_STUDENT.toString());

        students = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Student student = new Student();
            student.setId("st" + i);
            student.setEmail("student" + i);
            student.setPassword("1234");
            student.setFirstName("student_first_name" + i);
            student.setLastName("student_last_name" + i);
            student.setEnable(true);
            student.getRoles().add(studentRole);
            students.add(student);

        }

        userRepository.saveAll(students);
    }

    private void createTeacher() {

        Role teacherRole = roleRepository.findByRole(RoleValues.ROLE_TEACHER.toString());

        teacher = new Teacher();
        teacher.setId("tch");
        teacher.setEmail("teacher");
        teacher.setPassword("1234");
        teacher.setFirstName("teacher_first_name");
        teacher.setLastName("teacher_last_name");
        teacher.setEnable(true);
        teacher.getRoles().add(teacherRole);

        userRepository.save(teacher);

    }

    private void createGroup() {

        group = new Group();
        group.setId("gr");
        group.setName("IS-43");
        group.setStudents(students);
        group.setTeacher(teacher);

        Group group = groupRepository.save(this.group);

        students.forEach(student -> student.setGroup(group));
        userRepository.saveAll(students);

    }

    private void usersForConfirmation() {
        Role studentRole = roleRepository.findByRole(RoleValues.ROLE_STUDENT.toString());
        Role teacherRole = roleRepository.findByRole(RoleValues.ROLE_TEACHER.toString());

        Student student = new Student("studentForConfirm", "test", studentRole);
        student.setId("stForConfirm");
        userRepository.save(student);

        Teacher teacher = new Teacher("teacherForConfirm", "test", teacherRole);
        teacher.setId("tchForConfirm");
        userRepository.save(teacher);
    }

}
