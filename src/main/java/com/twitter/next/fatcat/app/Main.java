package com.twitter.next.fatcat.app;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.twitter.next.fatcat.collections.TransactionSingleton;
import com.twitter.next.fatcat.collections.UserSingleton;
import com.twitter.next.fatcat.entities.User;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan({ "com.twitter.next.fatcat.controllers", "com.twitter.next.fatcat.entities",
		"com.twitter.next.fatcat.repositories" })
@SpringBootApplication
@EnableSwagger2
public class Main implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	TransactionSingleton trxSingleton = TransactionSingleton.getInstance();
	UserSingleton userSingleton = UserSingleton.getInstance();

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u = new User();
		u.setId("1102321381"); // TODO(dahlquist) : this id will need to be update to the actual b6live6 user's
								// id
		u.setUsername("@bslive6");
		u.setCashappid("SHOPPABLE_TWEETS");
		u.setCashappsecret("CASH_jvqrtqvfzwb0zb3y26gwqnnbe");

		// Add universal user
		userSingleton.put(u.getId(), u);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();
	}

}
