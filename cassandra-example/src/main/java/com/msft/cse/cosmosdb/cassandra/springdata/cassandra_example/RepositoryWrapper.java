package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryWrapper {

	@Autowired
	BasicUserRepository customerRepository;
	
	public List<Person> findUsersByNameStartsWith(String name) {
		return customerRepository.findUsersByNameStartsWith(name);
	}
	
}
