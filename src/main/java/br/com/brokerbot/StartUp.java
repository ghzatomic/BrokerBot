package br.com.brokerbot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@ComponentScan( "br.com.brokerbot" )
//Enable Spring/jpa transaction management.
@EnableTransactionManagement

@EnableJpaRepositories(basePackages = {"br.com.brokerbot.data.repository"}, 
     repositoryBaseClass =  br.com.brokerbot.data.repository.BaseRepositoryImpl.class)
public class StartUp {

	public static void main(String[] args) {
		SpringApplication.run(StartUp.class, args);
	}
}
