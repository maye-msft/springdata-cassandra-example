/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;

import org.springframework.data.repository.CrudRepository;

/**
 * Simple repository interface for {@link User} instances. The interface is used
 * to declare so called query methods, methods to retrieve single entities or
 * collections of them.
 *
 * @author Thomas Darimont
 */
public interface BasicUserRepository extends CrudRepository<Person, Long> {

	/**
	 * Sample method annotated with {@link Query}. This method executes the CQL from
	 * the {@link Query} value.
	 *
	 * @param id
	 * @return
	 */
	@Query("SELECT * from person where id in(?0)")
	Person findUserByIdIn(String id);

	/**
	 * Derived query method. This query corresponds with
	 * {@code SELECT * FROM users WHERE uname = ?0}. {@link User#username} is not
	 * part of the primary so it requires a secondary index.
	 *
	 * @param username
	 * @return
	 */
	@AllowFiltering
	Person findUserByName(String name);

	/**
	 * Derived query method using SASI (SSTable Attached Secondary Index) features
	 * through the {@code LIKE} keyword. This query corresponds with
	 * {@code SELECT * FROM users WHERE lname LIKE '?0'}. {@link User#lastname} is
	 * not part of the primary key so it requires a secondary index.
	 *
	 * @param lastnamePrefix
	 * @return
	 */
	@AllowFiltering
	List<Person> findUsersByNameStartsWith(String name);

}
