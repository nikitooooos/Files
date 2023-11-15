package model;

import java.util.ArrayList;
import java.util.List;

public class Human {
    private String name;
    private Integer age;
    private final List<String> hobbies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getNavigationSystem() {
        return hobbies;
    }
}