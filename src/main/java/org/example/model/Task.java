package org.example.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Task {
    private final String name;
    private final LocalDate date;
    private final Double timeAmount;
    private List<Project> projects;

    public Task(String name, LocalDate localDate, Double timeAmount, List<Project> projects) {
        this.name = name;
        this.date = localDate;
        this.timeAmount = timeAmount;
        this.projects = projects;
    }

    public void addProject(Project project){
        this.projects.add(project);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", localDate=" + date +
                ", timeAmount=" + timeAmount +
                ", projects=" + projects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && Objects.equals(date, task.date) && Objects.equals(timeAmount, task.timeAmount) && Objects.equals(projects, task.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, timeAmount, projects);
    }
}
