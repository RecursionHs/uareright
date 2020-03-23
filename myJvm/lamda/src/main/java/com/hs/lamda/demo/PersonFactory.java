package com.hs.lamda.demo;

public interface PersonFactory<p extends Person> {
    p create(String firstName,String lastName);
}
