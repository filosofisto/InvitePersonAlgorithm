package challenge;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/*
The HR Department of a big corporation is arranging a party to facilitate interpersonal communication
between employees. There are a lot of people working here, some of them know each other some - don’t.
That’s why it was decided to 1) invite as many people as possible, 2) invite only those who aren't
acquainted with other invited employees. Since it might take a huge amount of time to solve this problem
by the HR Department itself, this task was delegated to the IT Department. Developers were asked to
write some code script that can solve the problem for a given set of employees and their acquaintances.
 */
public class InvitingPeople {

    /**
     * Universe of people available to be invited (employees)
     */
    private List<Person> people;
    private int maxInvited;
    private List<Person> maxInvitedList;

    public InvitingPeople(List<Person> people) {
        this.people = people;

        // hold max length list (it is possible that exists more than one)
        maxInvitedList = new ArrayList<>();

        // hold max quantity of invited people
        int maxInvited = 0;

    }

    /**
     * Return the list of invited people following these rules:
     *
     * 1) invite as many people as possible
     * 2) invite only those who aren't acquainted with other invited employees
     *
     * @return
     */
    public List<Person> inviteMaxNonCoworker() {
        heapPermutation(people, people.size(), people.size());

        return maxInvitedList;
    }

    /**
     * Return a Stream of People less me.
     *
     * @param invited
     * @param me
     * @return
     */
    private Stream<Person> invitedLessMe(List<Person> invited, Person me) {
        return invited.stream().filter(p -> !p.getName().equals(me.getName()));
    }

    /**
     * true indicates that me does not know any coworkers of invited Stream.
     *
     * @param invited
     * @param me
     * @return
     */
    private boolean doNotKnowAnyone(Stream<Person> invited, Person me) {
        return invited.noneMatch(p -> me.doIKnowYou(p));
    }

    void heapPermutation(List<Person> list, int size, int n) {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1) {
            calculateInvites(list);
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(list,size - 1, n);

            // if size is odd, swap 0th i.e (first) and
            // (size-1)th i.e (last) element
            // If size is even, swap ith
            // and (size-1)th i.e last element
            if (size % 2 == 1) {
                Person temp = list.get(0);
                list.set(0, list.get(size - 1));
                list.set(size-1, temp);
            } else {
                Person temp = list.get(i);
                list.set(i, list.get(size - 1));
                list.set(size - 1, temp);
            }
        }
    }

    private void calculateInvites(List<Person> list) {
        List<Person> invited = new ArrayList<>();

        list.forEach(person -> {
            if (doNotKnowAnyone(invitedLessMe(invited, person), person)) {
                invited.add(person);
            }
        });

        if (invited.size() > maxInvited) {
            maxInvited = invited.size();
            maxInvitedList = invited;
        }
    }
}

