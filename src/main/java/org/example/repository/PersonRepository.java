package org.example.repository;

import lombok.Builder;
import lombok.Data;
import org.example.model.Person;

import java.util.List;
@Data
@Builder
public class PersonRepository {
    private List<Person> people;
}
