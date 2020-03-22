package com.hs.functionInterface;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Person {
    String name;
    String age;

    Person(){
    }

    Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Supplier<Person> personSupplier = Person::new;
        System.out.println(personSupplier.get());//com.hs.functionInterface.Person@b4c966a

        Consumer<Person> personConsumer = (p) -> System.out.println("hello:" + p.name);
        personConsumer.accept(new Person("tony","18"));

        Comparator<Person> comparator = (p1,p2) -> p1.name.compareTo(p2.name);
        Person p1 = new Person("tony", "18");
        Person p2 = new Person("jerry", "18");
        System.out.println(comparator.compare(p1,p2));//>0
        System.out.println(comparator.reversed().compare(p1,p2));//<0
        System.out.println("--------------------------");
        Optional<Person> optional = Optional.of(p1);//true
        System.out.println(optional.isPresent());//com.hs.functionInterface.Person@7cc355be
        System.out.println(optional.get());//tony
        System.out.println(optional.orElse(p2).name);//tony
        optional.ifPresent((p) -> System.out.println(p.name));

    }
}
