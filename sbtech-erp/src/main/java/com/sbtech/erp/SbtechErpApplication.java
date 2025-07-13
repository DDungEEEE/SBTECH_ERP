package com.sbtech.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
		"com.sbtech"
})
@EnableScheduling
@Import(com.sbtech.external.ExternalDataSourceConfig.class)
public class SbtechErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbtechErpApplication.class, args);
	}

}
