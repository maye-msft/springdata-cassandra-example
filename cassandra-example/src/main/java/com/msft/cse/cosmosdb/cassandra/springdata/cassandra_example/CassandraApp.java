package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CassandraApp  implements CommandLineRunner{

  private static final Logger LOGGER = LoggerFactory.getLogger(CassandraApp.class);
  
  @Autowired
  BasicUserRepository customerRepository;



  public static void main(String[] args) {
	  SpringApplication.run(CassandraApp.class, args);
    
  }

	@Override
	public void run(String... args) throws Exception {
		Person personSaved = customerRepository.save(Person.newPerson("Person1", 41));
		Person person = customerRepository.findUserByIdIn(personSaved.getId());
		System.out.println(person.getName());
		person = customerRepository.findUserByName(personSaved.getName());
		LOGGER.info("Age:"+person.getAge());
		customerRepository.save(Person.newPerson("Person2", 22));
		List<Person> list = customerRepository.findUsersByNameStartsWith("Person");
		for(Person p : list) {
			System.out.println(p.getName());
		}
		
//		Cluster cluster = Cluster.builder().addContactPoints("localhost").build();
//	    Session session = cluster.connect("mykeyspace");
//
//	    CassandraOperations template = new CassandraTemplate(session);
//
//	    Person jonDoe = template.insert(Person.newPerson("Jon Doe", 40));
//
//	    LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getName());
////	    LOGGER.info(template.selectOne(Query.query(Criteria.where("name").is(jonDoe.getName())), Person.class).getName());
//
//	    template.truncate(Person.class);
//	    session.close();
//	    cluster.close();
		
	}
}
