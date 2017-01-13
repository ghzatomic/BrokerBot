package br.com.brokerbot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan( "br.com.brokerbot" )
public class StartUp {

	public static void main(String[] args) {
		SpringApplication.run(StartUp.class, args);
	}
}
