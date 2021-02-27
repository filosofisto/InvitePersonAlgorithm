package challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@EqualsAndHashCode(exclude = {"coworkers"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private String name;
    private Map<String, Person> coworkers;

    public Person addCoworker(Person coworker) {
        coworkers.put(coworker.getName(), coworker);
        return this;
    }

    public boolean doIKnowYou(Person other) {
        return coworkers.containsKey(other.getName());
    }
}
