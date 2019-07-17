package com.oocl.web.sampleWebApp.jpaSample.entity;

import javax.persistence.*;

@Entity
@Table(name = "SINGLE_ENTITY")
public class SingleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="NAME", length = 64, nullable = false)
    private String name;

    @Column(name ="AGE")
    private int age;

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
