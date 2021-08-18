package org.quickstart.javase.jdk8.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ListSortTest {

    // [Java 8 – Powerful Comparison with Lambdas](https://www.baeldung.com/java-8-sort-lambda)
    // [JDK 8 之 Stream sorted() 示例](https://blog.csdn.net/lsgqjh/article/details/63686383)
    // [Java 8 – How to Sort List with Stream.sorted()](https://stackabuse.com/java-8-how-to-sort-list-with-stream-sorted/)
    // [java 8 Collections 多字段排序](https://www.jianshu.com/p/d181129d47e1)

    // [jdk1.8之前的做法参考](http://blog.csdn.net/enable1234___/article/details/53224740)
    // [jdk1.8新特性的做法参考](http://blog.csdn.net/aitangyong/article/details/54880228)

    @Test
    public void testSortList() throws ParseException {
        List<Student> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        list.add(new Student(1, "Mahesh", 12, sdf.parse("2007/04/01")));
        list.add(new Student(2, "Suresh", 15, sdf.parse("2007/05/01")));
        list.add(new Student(3, "Nilesh", 10, sdf.parse("2006/06/01")));
        list.add(new Student(3, "xixixi", 15, sdf.parse("2006/04/01")));
        list.add(new Student(3, "lilili", 17, sdf.parse("2006/05/01")));
        list.add(new Student(3, "wangwa", 19, sdf.parse("2006/06/01")));

        System.out.println("---Natural Sorting by Name---");
        List<Student> slist = list.stream().sorted().collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

        System.out.println("---Natural Sorting by Name in reverse order---");
        slist = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

        System.out.println("---Sorting using Comparator by Age---");
        slist = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

        System.out.println("---Sorting using Comparator by Age with reverse order---");
        slist = list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge()));

        List<Student> couponList = list.stream().sorted(Comparator.comparing(Student::getId)//
                .thenComparing(c -> c.getBirthday().compareTo(new Date()))//
                .thenComparing(Student::getName)//
                .thenComparing(Student::getAge))//
            .collect(Collectors.toList());

        // age升序
        Comparator<Student> byIdASC = Comparator.comparing(Student::getAge);

        // named不分区大小写降序
        Comparator<Student> byNameDESC = Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER).reversed();

        // birthday 降序
        Comparator<Student> byBirthdayDESC = Comparator.comparing(Student::getBirthday).reversed();

        // 联合排序
        Comparator<Student> finalComparator = byIdASC.thenComparing(byNameDESC).thenComparing(byBirthdayDESC);

        List<Student> result = list.stream().sorted(finalComparator).collect(Collectors.toList());
        // print(result);

    }

    //Basic Sort Without Lambdas
    @Test
    public void givenPreLambda_whenSortingEntitiesByName_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 10),//
            new Human("Jack", 12)//
        );

        Collections.sort(humans, new Comparator<Human>() {
            @Override
            public int compare(Human h1, Human h2) {
                return h1.getName().compareTo(h2.getName());
            }
        });
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    //Basic Sort With Lambda Support
    @Test
    public void whenSortingEntitiesByName_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 10),//
            new Human("Jack", 12)//
        );

        humans.sort((Human h1, Human h2) -> h1.getName().compareTo(h2.getName()));

        assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public void givenLambdaShortForm_whenSortingEntitiesByName_thenCorrectlySorted() {

        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 10),//
            new Human("Jack", 12)//
        );

        humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));

        assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public void givenMethodDefinition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {

        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 10),//
            new Human("Jack", 12)//
        );

        humans.sort(Human::compareByNameThenAge);
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public void givenInstanceMethod_whenSortingEntitiesByName_thenCorrectlySorted() {

        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 10),//
            new Human("Jack", 12)//
        );

        Collections.sort(humans, Comparator.comparing(Human::getName));
        assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public void whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 10),//
            new Human("Jack", 12)//
        );

        Comparator<Human> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());

        humans.sort(comparator.reversed());

        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public void whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 12),//
            new Human("Sarah", 10),//
            new Human("Zack", 12)//
        );

        humans.sort((lhs, rhs) -> {
            if (lhs.getName().equals(rhs.getName())) {
                return Integer.compare(lhs.getAge(), rhs.getAge());
            } else {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public void givenComposition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {

        List<Human> humans = Lists.newArrayList(//
            new Human("Sarah", 12),//
            new Human("Sarah", 10),//
            new Human("Zack", 12)//
        );

        humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));

        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public final void givenStreamNaturalOrdering_whenSortingEntitiesByName_thenCorrectlySorted() {
        List<String> letters = Lists.newArrayList("B", "A", "C");

        List<String> sortedLetters = letters.stream().sorted().collect(Collectors.toList());
        assertThat(sortedLetters.get(0), equalTo("A"));
    }

    @Test
    public final void givenStreamCustomOrdering_whenSortingEntitiesByName_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        Comparator<Human> nameComparator = (h1, h2) -> h1.getName().compareTo(h2.getName());

        List<Human> sortedHumans = humans.stream().sorted(nameComparator).collect(Collectors.toList());
        assertThat(sortedHumans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public final void givenStreamComparatorOrdering_whenSortingEntitiesByName_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        List<Human> sortedHumans = humans.stream()//
            .sorted(Comparator.comparing(Human::getName))//
            .collect(Collectors.toList());

        assertThat(sortedHumans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public final void givenStreamNaturalOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        List<String> letters = Lists.newArrayList("B", "A", "C");

        List<String> reverseSortedLetters = letters.stream()//
            .sorted(Comparator.reverseOrder())//
            .collect(Collectors.toList());

        assertThat(reverseSortedLetters.get(0), equalTo("C"));
    }

    @Test
    public final void givenStreamCustomOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        Comparator<Human> reverseNameComparator = (h1, h2) -> h2.getName().compareTo(h1.getName());

        List<Human> reverseSortedHumans = humans.stream().sorted(reverseNameComparator).collect(Collectors.toList());
        assertThat(reverseSortedHumans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public final void givenStreamComparatorOrdering_whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        List<Human> reverseSortedHumans = humans.stream()//
            .sorted(Comparator.comparing(Human::getName, Comparator.reverseOrder()))//
            .collect(Collectors.toList());

        assertThat(reverseSortedHumans.get(0), equalTo(new Human("Sarah", 10)));
    }

    // Null Values
    @Test(expected = NullPointerException.class)
    public void givenANullElement_whenSortingEntitiesByName_thenThrowsNPE() {
        List<Human> humans = Lists.newArrayList(null, new Human("Jack", 12));

        humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));
    }

    @Test
    public void givenANullElement_whenSortingEntitiesByNameManually_thenMovesTheNullToLast() {
        List<Human> humans = Lists.newArrayList(null, new Human("Jack", 12), null);

        humans.sort((h1, h2) -> {
            if (h1 == null) {
                return h2 == null ? 0 : 1;
            } else if (h2 == null) {
                return -1;
            }
            return h1.getName().compareTo(h2.getName());
        });

        Assert.assertNotNull(humans.get(0));
        Assert.assertNull(humans.get(1));
        Assert.assertNull(humans.get(2));
    }

    @Test
    public void givenANullElement_whenSortingEntitiesByName_thenMovesTheNullToLast() {
        List<Human> humans = Lists.newArrayList(null, new Human("Jack", 12), null);

        humans.sort(Comparator.nullsLast(Comparator.comparing(Human::getName)));

        Assert.assertNotNull(humans.get(0));
        Assert.assertNull(humans.get(1));
        Assert.assertNull(humans.get(2));
    }

    @Test
    public void givenANullElement_whenSortingEntitiesByName_thenMovesTheNullToStart() {
        List<Human> humans = Lists.newArrayList(null, new Human("Jack", 12), null);

        humans.sort(Comparator.nullsFirst(Comparator.comparing(Human::getName)));

        Assert.assertNull(humans.get(0));
        Assert.assertNull(humans.get(1));
        Assert.assertNotNull(humans.get(2));
    }

    @Data
    @AllArgsConstructor
    static class Human {
        private String name;
        private int age;

        // standard constructors, getters/setters, equals and hashcode

        public static int compareByNameThenAge(Human lhs, Human rhs) {
            if (lhs.name.equals(rhs.name)) {
                return Integer.compare(lhs.age, rhs.age);
            } else {
                return lhs.name.compareTo(rhs.name);
            }
        }
    }

    @Getter
    @AllArgsConstructor
    public class Student implements Comparable<Student> {
        private int id;
        private String name;
        private int age;
        private Date birthday;

        @Override
        public int compareTo(Student ob) {
            return name.compareTo(ob.getName());
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            final Student std = (Student)obj;
            if (this == std) {
                return true;
            } else {
                return (this.name.equals(std.name) && (this.age == std.age));
            }
        }

        @Override
        public int hashCode() {
            int hashno = 7;
            hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
            return hashno;
        }
    }
}
