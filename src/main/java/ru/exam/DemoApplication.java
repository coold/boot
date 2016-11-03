package ru.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import ru.exam.config.BaseConfig;
import ru.exam.config.JpaConfig;
import ru.exam.dao.PersonDAO;
import ru.exam.model.Person;
import ru.exam.repository.PersonRepository;

import java.util.concurrent.*;

@SpringBootApplication(scanBasePackageClasses = {JpaConfig.class, BaseConfig.class})
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		PersonDAO personDAO = context.getBean(PersonDAO.class);
		PersonRepository repository = context.getBean(PersonRepository.class);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Person person = new Person();
		person.setName("asd");
		person.setAge(34);
		personDAO.savePerson(person);

		Future<?> submit = executorService.submit(() -> personDAO.savePersonLong(person));
		Future<?> submit1 = executorService.submit(() -> personDAO.readPerson(person));
		try {
			submit.get();
			submit1.get();
		} catch (Exception e) {
		}

		System.out.println("END:" + repository.findOne(person.getId()));
	}
}
