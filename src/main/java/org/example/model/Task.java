package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class Task {
    private String name;
    private String date;
    private String timeAmount;
    private List<Project> projects;

    public void addProject(Project project){
        this.projects.add(project);
    }
}
