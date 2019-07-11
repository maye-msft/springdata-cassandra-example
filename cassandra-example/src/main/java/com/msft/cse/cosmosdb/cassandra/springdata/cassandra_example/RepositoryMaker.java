package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mdkt.compiler.InMemoryJavaCompiler;
import org.springframework.context.ApplicationContext;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.repository.support.CassandraRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryComposition.RepositoryFragments;

public class RepositoryMaker<T> {

	private ApplicationContext context;
	private InMemoryJavaCompiler compiler;
	
	//to do 
	private Map<String, Class> cache;

	public RepositoryMaker(ApplicationContext context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public T queryOne(Class<T> cls, String methodName, List<Object> params, Class<?>... paramTypes) throws Exception {
		StringBuilder sourceCode = new StringBuilder();
		sourceCode.append("package com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example;\n");
		sourceCode.append("import org.springframework.boot.autoconfigure.security.SecurityProperties.User;\n");
		sourceCode.append("import org.springframework.data.cassandra.repository.AllowFiltering;\n");
		sourceCode.append("import org.springframework.data.cassandra.repository.Query;\n");
		sourceCode.append("import org.springframework.data.repository.CrudRepository;\n");
		sourceCode.append("import com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example."+cls.getSimpleName()+";\n");
		
		sourceCode.append("\n");
		sourceCode.append("public interface TestgeneratedRepository extends CrudRepository<"+ cls.getSimpleName()+", Long> {\n");
		sourceCode.append("   @AllowFiltering\n");
		sourceCode.append("   "+cls.getSimpleName()+" "+methodName+"(String name);\n");
		sourceCode.append("}");
		
//		System.out.println(sourceCode.toString());
		ClassLoader classLoader = org.springframework.util.ClassUtils.getDefaultClassLoader();
		compiler = InMemoryJavaCompiler.newInstance();
		compiler.useParentClassLoader(classLoader);
		Class<?> testRepository = compiler.compile("com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example.TestgeneratedRepository", sourceCode.toString());
		
//		Class<?> class1 = compiler.getClassloader().loadClass("com.msft.cse.cosmosdb.cassandra.springdata.cassandra_example.TestgeneratedRepository");
//		System.out.println(class1);
//		System.out.println(testRepository);
		
		CassandraSessionFactoryBean bean2 = context.getBean(CassandraSessionFactoryBean.class);
		RepositoryFragments repositoryFragmentsToUse = (RepositoryFragments) Optional.empty() //
				.orElseGet(RepositoryFragments::empty); //
		
		CassandraRepositoryFactory factory = new CassandraRepositoryFactory(
				new CassandraAdminTemplate(bean2.getObject(), bean2.getConverter()));
		
		factory.setBeanClassLoader(compiler.getClassloader());

		Object repository = factory.getRepository(testRepository, repositoryFragmentsToUse);
		System.out.println(repository);

		Method method = repository.getClass().getMethod(methodName, paramTypes);
		@SuppressWarnings("unchecked")
		T obj = (T) method.invoke(repository, params.toArray());
		
		
		
//		CassandraSessionFactoryBean bean2 = context.getBean(CassandraSessionFactoryBean.class);
//		RepositoryFragments repositoryFragmentsToUse = (RepositoryFragments) Optional.empty() //
//				.orElseGet(RepositoryFragments::empty); //
//		CassandraRepositoryFactory factory = new CassandraRepositoryFactory(
//				new CassandraAdminTemplate(bean2.getObject(), bean2.getConverter()));
//		BasicUserRepository repository = factory.getRepository(BasicUserRepository.class, repositoryFragmentsToUse);
//		System.out.println(repository);
//		Method method = testRepository.getMethod(methodName, paramTypes);
//		T obj = (T) method.invoke(testRepository.newInstance(), params.toArray());
//		
		return obj;
	}
	
	public static void main(String args[]) {
		
	}
	
}
