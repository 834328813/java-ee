package com.changwen.hibernate4.mapped.union.subclass;

/**
 * Person
 *
 * @author lcw 2015/12/23
 */
public class Person {
    private Integer id;
    private String name;
    private int age;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
