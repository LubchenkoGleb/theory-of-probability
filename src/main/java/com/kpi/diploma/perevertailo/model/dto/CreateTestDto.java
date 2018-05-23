package com.kpi.diploma.perevertailo.model.dto;

import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateTestDto {

    @NotNull
    private List<String> taskIds;

    @NonNull
    private String name;

    @NotNull
    private ThemeValues theme;

    public CreateTestDto() {
    }
}
