package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Person {
    private List<Task> tasks;
    private final String name;

    public void addTask(Task task){
        this.tasks.add(task);
    }
}
