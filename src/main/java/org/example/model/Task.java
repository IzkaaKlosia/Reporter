package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class Task {
    private  String name;
    private  LocalDate date;
    private  Double timeAmount;
    private Project project;
}
