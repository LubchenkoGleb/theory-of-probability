package com.kpi.diploma.perevertailo.model.document;

import com.kpi.diploma.perevertailo.model.document.user.Student;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Journal {

    @Id
    private String id;

    @DBRef(lazy = true)
    private Group group;

    @DBRef(lazy = true)
    private List<Student> students;

}
