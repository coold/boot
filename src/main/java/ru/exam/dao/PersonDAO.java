package ru.exam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.exam.model.Person;
import ru.exam.repository.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by rz on 30.10.2016.
 */
@Service
public class PersonDAO {

    @Autowired
    PersonRepository personRepository;

    @Transactional
    public void savePerson(Person person) {

        personRepository.save(person);
        System.out.println("init" + person);

    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
    public void savePersonLong(Person person) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Person one = personRepository.findOne(person.getId());
        one.setName("Ololo");
        personRepository.save(one);
        System.out.println("Changed!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end save " + personRepository.findOne(person.getId()));
        //throw new UnsupportedOperationException();
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void readPerson(Person person) {
        Person person4 = personRepository.findOne(person.getId());
        System.out.println("Read1 " + person4);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Person person1 = personRepository.findOne(person.getId());
        System.out.println("Read2 " + person1);
    }

    public void init(){

    }
}
