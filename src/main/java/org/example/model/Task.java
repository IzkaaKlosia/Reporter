package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class Task {
    private String name;
    private LocalDate date;
    private Double timeAmount;
    //private List<Project> projects;
    private Project project;

//    //public void addProject(Project project){
//        this.projects.add(project);
    }

