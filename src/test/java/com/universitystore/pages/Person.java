package com.universitystore.pages;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Value;

@Value
public class Person {
    String name;
    int age;
    String nationality;
}
