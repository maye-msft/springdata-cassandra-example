package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.support.CassandraRepositoryFactory;
import org.springframework.data.cassandra.repository.support.CassandraRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryComposition.RepositoryFragments;




@SpringBootApplication
public class CassandraGenericRepoApp implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraGenericRepoApp.class);

	@Autowired
	BasicUserRepository customerRepository;
	
	  @Autowired
	  private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(CassandraGenericRepoApp.class, args);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {
		



		
		Person personSaved = customerRepository.save(Person.newPerson("Person1", 41));
//		Person person = customerRepository.findUserByIdIn(personSaved.getId());
//		System.out.println(person.getName());
//		person = customerRepository.findUserByName(personSaved.getName());
//		System.out.println("Age:" + person.getAge());
//		System.out.println("Pic Length:" + person.getPict().limit());
//
//		customerRepository.save(Person.newPerson("Person2", 22));
//		List<Person> list = customerRepository.findUsersByNameStartsWith("Person");
//		for(Person p : list) {
//			System.out.println(p.getName());
//		}
		
		
		try {
			RepositoryMaker<Person> maker = new RepositoryMaker<Person>(context);
			Person p = maker.queryOne(Person.class, "findUserByName", Arrays.asList(new String[]{"Person1"}), String.class);
			System.out.println(p.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	}
}
