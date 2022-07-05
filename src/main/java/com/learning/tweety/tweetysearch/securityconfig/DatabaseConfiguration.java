package com.learning.tweety.tweetysearch.securityconfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.tweety.tweetysearch.credentials.Credentials;
import com.learning.tweety.tweetysearch.services.common.RestClientService;

@Configuration
public class DatabaseConfiguration {
	
	@Autowired
	RestClientService clientService;
	@Autowired
	ApplicationProperties property;
	
	@SuppressWarnings("rawtypes")
	@Bean
	public DataSource getDataSource() {
		Credentials creds = clientService.getCredentials(property.getEnvName());
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(creds.getServerUrl());
		dataSourceBuilder.username(creds.getUserName());
		dataSourceBuilder.password(creds.getPassWord());
		return dataSourceBuilder.build();
	}
}
