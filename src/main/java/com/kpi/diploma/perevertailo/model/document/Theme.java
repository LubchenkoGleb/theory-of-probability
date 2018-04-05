package com.kpi.diploma.perevertailo.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Theme {

    @Id
    private String id;

    private String  description;

    private String fullName;

}
