package com.kpi.diploma.perevertailo.model.document;

import com.kpi.diploma.perevertailo.model.document.user.Student;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Journal {

    @Id
    private String id;

    @DBRef
    private Group group;

    @DBRef
    private List<Student> students;

}
