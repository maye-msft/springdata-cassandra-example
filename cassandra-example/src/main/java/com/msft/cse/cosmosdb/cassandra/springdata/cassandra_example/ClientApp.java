package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class ClientApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraApp.class);

	public static void main(String[] args) {
		Cluster cluster = Cluster.builder().addContactPoints("localhost").build();
		Session session = cluster.connect("mykeyspace");

		CassandraOperations template = new CassandraTemplate(session);

		Person jonDoe = template.insert(Person.newPerson("Jon Doe", 40));

		LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getName());

		template.truncate(Person.class);
		session.close();
		cluster.close();
	}

}
