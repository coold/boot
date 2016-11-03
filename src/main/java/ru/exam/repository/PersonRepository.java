package ru.exam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.exam.model.Person;

/**
 * Created by rz on 30.10.2016.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
