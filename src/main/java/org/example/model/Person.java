package org.example.model;

import java.util.List;
import java.util.Objects;

public class Person {
    private List<Task> tasks;
    private final String name;

    public Person(List<Task> tasks, String name) {
        this.tasks = tasks;
        this.name = name;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        return "Person{" +
                "tasks=" + tasks +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(tasks, person.tasks) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks, name);
    }
}
