package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.SASI;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Person {

  @PrimaryKey
  private final String id;

  @SASI
  private final String name;
  
  private final int age;

  public Person(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return String.format("{ @type = %1$s, id = %2$s, name = %3$s, age = %4$d }",
      getClass().getName(), getId(), getName(), getAge());
  }
  
  protected static Person newPerson(String name, int age) {
	    return newPerson(UUID.randomUUID().toString(), name, age);
	  }

	  protected static Person newPerson(String id, String name, int age) {
	    return new Person(id, name, age);
	  }
}