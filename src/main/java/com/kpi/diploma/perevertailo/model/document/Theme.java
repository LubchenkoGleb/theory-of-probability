package com.kpi.diploma.perevertailo.model.document;

import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Theme {

    @Id
    private String id;

    private ThemeValues value;

    private String fullName;

    public Theme() {
    }

    public Theme(ThemeValues value, String fullName) {
        this.value = value;
        this.fullName = fullName;
    }
}
