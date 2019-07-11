# springdata-cassandra-example
A workable version SpringData with Cassandra


It show the feature of 
- save
- findById
- findByName
- findByNamePrefix

### Add Binary data 2019-07-08

Add ByteBuffer field 'pict' in Person.java 

```java
private ByteBuffer pict;
```

Add an image file in resources folder. All the person instances will load the png file and save into Cassandra. in the CassandraApp.java, it will do insert and query, after query the code will output the image file length to verfiy it is inserted.

```java
System.out.println("Pic Length:" + person.getPict().limit());
```
```
Pic Length:3557
```
