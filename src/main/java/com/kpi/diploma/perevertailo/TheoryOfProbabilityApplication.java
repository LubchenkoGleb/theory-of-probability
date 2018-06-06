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

        themeRepository.deleteAll();
        roleRepository.deleteAll();
        userRepository.deleteAll();
        groupRepository.deleteAll();
        taskRepository.deleteAll();
        testRepository.deleteAll();


        initRoles();
        intitThemes();
        createAdmin();
        createGroup(3);
        createStudents(2, "noGroup");
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

    private List<Student> createStudents(Integer amount, String groupName) {

        List<Student> students = new ArrayList<>();

        Role studentRole = roleRepository.findByRole(RoleValues.ROLE_STUDENT.toString());

        for (int i = 0; i < amount; i++) {

            Student student = new Student();
            student.setId("st" + groupName + i);
            student.setEmail("st" + groupName + i);
            student.setPassword(passwordEncoder.encode("1234"));
            student.setFirstName("stFtNm-" + groupName + i);
            student.setLastName("stLstName-" + groupName + i);
            student.setEnable(true);
            student.getRoles().add(studentRole);
            students.add(student);

        }

        return studentRepository.saveAll(students);
    }

    private void intitThemes() {

//        for (ThemeValues themeValues : ThemeValues.values()) {
//            themeRepository.save(new Theme(themeValues, themeValues.toString()));
//        }
        themeRepository.save(new Theme(ThemeValues.DEFINITION_PROBABILITIES,  "КЛАСИЧНЕ І СТАТИСТИЧНЕ ВИЗНАЧЕННЯ ІМОВІРНОСТІ"));
        themeRepository.save(new Theme(ThemeValues.ACTIONS_ON_EVENTS, "ДІЇ НАД ПОДІЯМИ. ТЕОРЕМА СКЛАДАННЯ ЙМОВІРНОСТЕЙ. ТЕОРЕМА МНОЖЕННЯ ІМОВІРНОСТЕЙ. ВИПАДКОВА ЙМОВІРНІСТЬ"));
        themeRepository.save(new Theme(ThemeValues.COMPLETE_PROBABILITY, "ФОРМУЛА ПОВНОЇ ІМОВІРНОСТІ. ФОРМУЛА Байєса"));
        themeRepository.save(new Theme(ThemeValues.FORMULA_BERNULI, "Формула Бернуллі. ФОРМУЛА Пуассона. ЛОКАЛЬНА І ІНТЕГРАЛЬНІ Теорема Лапласа"));
        themeRepository.save(new Theme(ThemeValues.VARIANCE_OF_RELATIVE_FREQUENCY, "ВІДХИЛЕННЯ ВІДНОСНОЇ ЧАСТОТИ ВІД ПОСТІЙНОЇ ІМОВІРНОСТІ В НЕЗАЛЕЖНИХ ВИПРОБУВАННЯХ. НАЙЙМОВІРНІСНІШЕ ЧИСЛО ПОЯВИ ПОДІЙ В НЕЗАЛЕЖНИХ ВИПРОБУВАННЯХ"));
        themeRepository.save(new Theme(ThemeValues.RANGE_OF_DISTRIBUTION,  "РЯД РОЗПОДІЛУ ДИСКРЕТНОЇ ВИПАДКОВОЇ ВЕЛИЧИНИ. ОСНОВНІ ЧИСЛОВІ ХАРАКТЕРИСТИКИ."));
        themeRepository.save(new Theme(ThemeValues.BASIC_LAWS_OF_DISTRIBUTION, "ОСНОВНІ ЗАКОНИ РОЗПОДІЛУ."));
        themeRepository.save(new Theme(ThemeValues.BASIC_NUMERICAL_CHARACTERISTICS, "ОСНОВНІ ЧИСЛОВІ ХАРАКТЕРИСТКИ"));
        themeRepository.save(new Theme(ThemeValues.SYSTEMS_OF_TWO_RANDOM_VALUES, "СИСТЕМИ ДВУХ ВИПАДКОВИХ ВЕЛИЧИН"));
        themeRepository.save(new Theme(ThemeValues.THE_LAW_OF_LARGE_NUMBERS, "ЗАКОН ВЕЛИКИХ ЧИСЕЛ"));
        themeRepository.save(new Theme(ThemeValues.SELECTIVE_METHOD,  "ВИБІРКОВИЙ МЕТОД"));
        themeRepository.save(new Theme(ThemeValues.STATISTICAL_ESTIMATION_OF_PARAMETERS_OF_DISTRIBUTION, "СТАТИСТИЧНІ ОЦІНКИ ПАРАМЕТРІВ РОЗПОДІЛУ"));
        themeRepository.save(new Theme(ThemeValues.METHODS_OF_ASSESSMENT_OF_THE_EVALUATION, "МЕТОДИ ЗНАХОДЖЕННЯ ОЦІНОК"));
        themeRepository.save(new Theme(ThemeValues.STATISTICAL_CHECK_OF_STATISTICAL_HYPOTHESIS, "СТАТИСТИЧНА ПЕРЕВІРКА СТАТИСТИЧНИХ ГІПОТЕЗ"));
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
