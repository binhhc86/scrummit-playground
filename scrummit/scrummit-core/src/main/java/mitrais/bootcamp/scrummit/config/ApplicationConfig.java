package mitrais.bootcamp.scrummit.config;

import mitrais.bootcamp.scrummit.multitenancy.MultiTenantMongoDbFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
// @EnableMongoRepositories(basePackages =
// "mitrais.bootcamp.scrummit.repositories")
@EnableAutoConfiguration
@PropertySource(value = "classpath:mongo-config.properties")
public class ApplicationConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.host}")
    private String mongoDBHost;

    @Value("${mongodb.port}")
    private int    mongoDBPort;

    @Value("${mongodb.db.central}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongo() throws Exception {

        return new MongoClient(mongoDBHost, mongoDBPort);
    }

    @Override
    protected String getMappingBasePackage() {
        return "mitrais.bootcamp.scrummit";
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Override
    @Bean
    public MultiTenantMongoDbFactory mongoDbFactory() throws Exception {
        return new MultiTenantMongoDbFactory(mongo(), databaseName);
    }

    public static void switchDbName(String dbName) {
        MultiTenantMongoDbFactory.setDatabaseNameForCurrentThread(dbName);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
