package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.document.task.Answer;
import com.kpi.diploma.perevertailo.model.document.task.PassedTestResponse;
import com.kpi.diploma.perevertailo.model.document.task.TestResult;
import com.kpi.diploma.perevertailo.model.document.user.Student;
import com.kpi.diploma.perevertailo.model.dto.StudentDto;
import com.kpi.diploma.perevertailo.model.dto.TestDto;

import java.util.List;

public interface StudentService {

    List<Student> getByGroup(String groupId);

    List<StudentDto> getAllStudents();

    List<Student> getStudentsWithoutGroup();

    PassedTestResponse passTest(String studentId, String testId, List<Answer> answers);

    List<Test> getNotPassedTests(String studentId);

    List<TestResult> getPassedTests(String studentId);

    TestDto getTestWitQuestions(String testId);
}
