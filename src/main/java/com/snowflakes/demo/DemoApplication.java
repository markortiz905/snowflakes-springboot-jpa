package com.snowflakes.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication {

	@Value("${snowflake.account}")
	private String account;
	@Value("${snowflake.warehouse}")
	private String warehouse;
	@Value("${snowflake.database.name}")
	private String dbName;
	@Value("${snowflake.schema}")
	private String schema;
	@Value("${snowflake.username}")
	private String username;
	@Value("${snowflake.region}")
	private String region;
	@Value("${snowflake.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		Properties properties = new Properties();
		properties.put("user", username);
		properties.put("password", password);
		properties.put("account", account);
		properties.put("warehouse", warehouse);
		properties.put("db", dbName);
		properties.put("schema", schema);
		//properties.put("passcode", System.getenv("PASSCODE"));
		String url = String.format("jdbc:snowflake://%s.%s.snowflakecomputing.com",account, region);
		return new SnowflakeBasicDataSourceExtended(properties, url);
	}
}
