package com.learning.tweety.tweetysearch.initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.learning.tweety.tweetysearch.securityconfig.ApplicationProperties;

@SpringBootApplication
@EnableJpaRepositories("com.learning.tweety")
@ComponentScan("com.learning.tweety")
@EntityScan("com.learning.tweety")
@EnableConfigurationProperties(ApplicationProperties.class)
@EnableJms
@EnableTransactionManagement
public class TweetySearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(TweetySearchApplication.class, args);
	}
}
