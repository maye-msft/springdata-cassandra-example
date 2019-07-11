package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.repository.support.CassandraRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryComposition.RepositoryFragments;

//@SpringBootApplication
public class CassandraManualRepoApp implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraManualRepoApp.class);

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(CassandraManualRepoApp.class, args);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {

		CassandraSessionFactoryBean bean2 = context.getBean(CassandraSessionFactoryBean.class);
		RepositoryFragments repositoryFragmentsToUse = (RepositoryFragments) Optional.empty() //
				.orElseGet(RepositoryFragments::empty); //
		CassandraRepositoryFactory factory = new CassandraRepositoryFactory(
				new CassandraAdminTemplate(bean2.getObject(), bean2.getConverter()));
		BasicUserRepository repository = factory.getRepository(BasicUserRepository.class, repositoryFragmentsToUse);
		System.out.println(repository);

		Person personSaved = repository.save(Person.newPerson("Person1", 41));
		Person person = repository.findUserByIdIn(personSaved.getId());
		System.out.println(person.getName());

	}
}
