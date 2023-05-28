import org.example.model.Person;
import org.example.model.Project;
import org.example.model.Task;
import org.example.printer.PrinterOnConsole;
import org.example.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.example.reports.Report1Generator.generateReport1;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Report1Spec {
    Project pr1 = Project.builder()
            .name("Projekt1")
            .build();

    Project pr2 = Project.builder()
            .name("Projekt2")
            .build();

    Task t1 = Task.builder()
            .name("bleble")
            .timeAmount(5.0)
            .project(pr1)
            .build();

    Task t2 = Task.builder()
            .name("XD")
            .timeAmount(2.0)
            .project(pr2)
            .build();

    Person p1 = Person.builder()
            .name("Kamil Z")
            .tasks(List.of(t1, t2))
            .build();

    Person p2 = Person.builder()
            .name("Zbigniew S")
            .tasks(List.of(t1))
            .build();

    List<Person> personsList = List.of(p1, p2);

    PersonRepository personRepository = PersonRepository.builder()
            .people(personsList)
            .build();

    @Test
    void testReport1(){
        Map<String, Double> testReport = generateReport1(personRepository);
        new PrinterOnConsole().printReport_1(generateReport1(personRepository));
        assertEquals(testReport.get("Projekt1"), 10.0, "Sum of time per project is not correct");
        assertEquals(testReport.get("Projekt2"), 2.0, "Sum of time per project is not correct");
    }
}
