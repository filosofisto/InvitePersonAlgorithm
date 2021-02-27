package challenge;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class InvitingPeopleTest {

    /**
     * people = []
     */
    @Test
    public void emptySet() {
        InvitingPeople invitingPeople = new InvitingPeople(Collections.emptyList());
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(0);
    }

    /**
     * people = [A]
     */
    @Test
    public void singlePerson() {
        Person a = Person.builder().name("A").coworkers(new HashMap<>()).build();
        InvitingPeople invitingPeople = new InvitingPeople(ListUtil.of(a));
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(1);
    }

    /**
     * people = [A,B]
     */
    @Test
    public void twoUnknownPeople() {
        Person a = Person.builder().name("A").coworkers(new HashMap<>()).build();
        Person b = Person.builder().name("B").coworkers(new HashMap<>()).build();
        InvitingPeople invitingPeople = new InvitingPeople(ListUtil.of(a, b));
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(2);
    }

    /**
     * people = [A,B && A<->B]
     */
    @Test
    public void twoKnownPeople() {
        Person a = Person.builder().name("A").coworkers(new HashMap<>()).build();
        Person b = Person.builder().name("B").coworkers(new HashMap<>()).build();

        a.addCoworker(b);
        b.addCoworker(a);

        InvitingPeople invitingPeople = new InvitingPeople(ListUtil.of(a, b));
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(1);
    }

    /**
     * people = [A,B,C]
     */
    @Test
    public void threeUnknownPeople() {
        Person a = Person.builder().name("A").coworkers(new HashMap<>()).build();
        Person b = Person.builder().name("B").coworkers(new HashMap<>()).build();
        Person c = Person.builder().name("C").coworkers(new HashMap<>()).build();

        InvitingPeople invitingPeople = new InvitingPeople(ListUtil.of(a, b, c));
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(3);
    }

    /**
     * people = [A,B,C && A<->B]
     */
    @Test
    public void threePeopleWithTwoKnown() {
        Person a = Person.builder().name("A").coworkers(new HashMap<>()).build();
        Person b = Person.builder().name("B").coworkers(new HashMap<>()).build();
        Person c = Person.builder().name("C").coworkers(new HashMap<>()).build();

        a.addCoworker(b);
        b.addCoworker(a);

        InvitingPeople invitingPeople = new InvitingPeople(ListUtil.of(a, b, c));
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(2);
    }

    /**
     * people = [A,B,C && A<->B, A<->C]
     */
    @Test
    public void threePeopleWithAKnownBAndC() {
        Person a = Person.builder().name("A").coworkers(new HashMap<>()).build();
        Person b = Person.builder().name("B").coworkers(new HashMap<>()).build();
        Person c = Person.builder().name("C").coworkers(new HashMap<>()).build();

        a.addCoworker(b);
        b.addCoworker(a);

        a.addCoworker(c);
        c.addCoworker(a);

        InvitingPeople invitingPeople = new InvitingPeople(ListUtil.of(a, b, c));
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(2);
    }

    /**
     * people = [A,B,C,D,E && A<->B, A<->C, A<->D, A<->E]
     */
    @Test
    public void threePeopleWithAKnownBAndCAndDAndE() {
        Person a = Person.builder().name("A").coworkers(new HashMap<>()).build();
        Person b = Person.builder().name("B").coworkers(new HashMap<>()).build();
        Person c = Person.builder().name("C").coworkers(new HashMap<>()).build();
        Person d = Person.builder().name("D").coworkers(new HashMap<>()).build();
        Person e = Person.builder().name("E").coworkers(new HashMap<>()).build();

        a.addCoworker(b);
        b.addCoworker(a);

        a.addCoworker(c);
        c.addCoworker(a);

        a.addCoworker(d);
        d.addCoworker(a);

        a.addCoworker(e);
        e.addCoworker(a);

        InvitingPeople invitingPeople = new InvitingPeople(ListUtil.of(a, b, c, d, e));
        assertThat(invitingPeople.inviteMaxNonCoworker()).size().isEqualTo(4);
    }
}
