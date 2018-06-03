package com.kpi.diploma.perevertailo.service.primary;

import com.kpi.diploma.perevertailo.model.document.Test;
import com.kpi.diploma.perevertailo.model.dto.CreateTestDto;
import com.kpi.diploma.perevertailo.model.dto.TestDto;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;

import java.util.List;

public interface TestService {

    List<TestDto> getAllByGroup(String groupId);

    List<TestDto> getNotAssignedToGroup(String groupId);

    Test deleteFromGroup(String groupId, String testId);

    Test addToGroup(String groupId, String testId);

    Test createTest(CreateTestDto createTestDto, String teacherId);

    List<TestDto> getByTheme(String theme);

    Test delete(String testId);
}
