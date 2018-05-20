package com.kpi.diploma.perevertailo;

import com.kpi.diploma.perevertailo.model.document.Group;
import com.kpi.diploma.perevertailo.model.document.Role;
import com.kpi.diploma.perevertailo.model.document.Theme;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.document.user.Teacher;
import com.kpi.diploma.perevertailo.model.document.user.User;
import com.kpi.diploma.perevertailo.model.util.value.RoleValues;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class TheoryOfProbabilityApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    private final RoleRepository roleRepository;

    private final GroupRepository groupRepository;

    private final PasswordEncoder passwordEncoder;

    private final ThemeRepository themeRepository;

    private final TaskRepository taskRepository;

    private final TestRepository testRepository;

    private User admin;

    private List<Group> groups = new ArrayList<>();


    @Autowired
    public TheoryOfProbabilityApplication(UserRepository userRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, RoleRepository roleRepository,
                                          GroupRepository groupRepository, PasswordEncoder passwordEncoder, ThemeRepository themeRepository, TaskRepository taskRepository, TestRepository testRepository) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.groupRepository = groupRepository;
        this.passwordEncoder = passwordEncoder;
        this.themeRepository = themeRepository;
        this.taskRepository = taskRepository;
        this.testRepository = testRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TheoryOfProbabilityApplication.class, args);
    }

    @Override
    public void run(String... strings) {

//        themeRepository.deleteAll();
//        roleRepository.deleteAll();
//        userRepository.deleteAll();
//        groupRepository.deleteAll();
//        taskRepository.deleteAll();
//        testRepository.deleteAll();
//
//
//        initRoles();
//        intitThemes();
//        createAdmin();
//        createGroup(3);
//        createStudents(2, "noGroup");
//        usersForConfirmation();

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

    private List<Student> createStudents(Integer amount, String groupName) {

        List<Student> students = new ArrayList<>();

        Role studentRole = roleRepository.findByRole(RoleValues.ROLE_STUDENT.toString());

        for (int i = 0; i < amount; i++) {

            Student student = new Student();
            student.setId("st" + groupName + i);
            student.setEmail("st" + groupName + i + "email.com");
            student.setPassword("1234");
            student.setFirstName("stFtNm-" + groupName + i);
            student.setLastName("stLstName-" + groupName + i);
            student.setEnable(true);
            student.getRoles().add(studentRole);
            students.add(student);

        }

        return studentRepository.saveAll(students);
    }

    private void intitThemes() {
        for (ThemeValues themeValues : ThemeValues.values()) {
            themeRepository.save(new Theme(themeValues, themeValues.toString()));
        }
    }


    private Teacher createTeacher(String groupName) {

        Role teacherRole = roleRepository.findByRole(RoleValues.ROLE_TEACHER.toString());

        Teacher teacher = new Teacher();
        teacher.setId("tch" + groupName);
        teacher.setEmail("tch" + groupName);
        teacher.setPassword(passwordEncoder.encode("1234"));
        teacher.setFirstName("tchFitNm-" + groupName);
        teacher.setLastName("tchLstName-" + groupName);
        teacher.setEnable(true);
        teacher.getRoles().add(teacherRole);

        return teacherRepository.save(teacher);
    }

    private void createGroup(Integer amount) {

        for (int i = 0; i < amount; i++) {

            String groupName = "IS4" + (i + 1);
            List<Student> students = createStudents(amount, groupName);
            Teacher teacher = createTeacher(groupName);

            Group group = new Group();
            group.setId("gr" + i);
            group.setName(groupName);
            group.setStudents(students);
            group.setTeacher(teacher);
            Group savedGroup = groupRepository.save(group);

            students.forEach(st -> st.setGroup(savedGroup));
            teacher.getGroups().add(savedGroup);

            studentRepository.saveAll(students);
            teacherRepository.save(teacher);
        }

    }

    private void usersForConfirmation() {
        Role studentRole = roleRepository.findByRole(RoleValues.ROLE_STUDENT.toString());
        Role teacherRole = roleRepository.findByRole(RoleValues.ROLE_TEACHER.toString());

        Student student = new Student("studentForConfirm", "test", studentRole);
        student.setId("stForConfirm");
        studentRepository.save(student);

        Teacher teacher = new Teacher("teacherForConfirm", "test", teacherRole);
        teacher.setId("tchForConfirm");
        teacherRepository.save(teacher);
    }

}
