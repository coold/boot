package ru.exam.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Created by rz on 30.10.2016.
 */
@Data
@Entity
public class Person {
    @Id
    private long id;
    private int age;
    private String name;
}
