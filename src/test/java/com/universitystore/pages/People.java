package com.universitystore.pages;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("person")
public record People(String name,  int age,String nationality) {
}
