package org.example.reports;

import org.example.model.Person;
import org.example.repository.PersonRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.reports.ReportGeneratorUtils.getTimeAmountFromTasks;

public class Report2Generator {

    public static Map<String, Double> generateReport2(PersonRepository personRepository){
       return personRepository.getPeople().stream()
               .collect(Collectors.toMap(
                       Person::getName,
                       v -> getTimeAmountFromTasks(v.getTasks())
               ));
    }

    // dla wersji 2b Osoba/ projekt / godzina
    // zrobic mape Map<Osoba, Map<NazwaProjektu, Liczba godzin>>
}
