package com.premierinc;

import com.premierinc.server.DumbFHIRRestfulServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 *
 */
@SpringBootApplication
public class SrtApplication {

	@Autowired
	private DumbFHIRRestfulServer dumbFHIRRestfulServer;

	public static void main(String[] args) {
		SpringApplication.run(SrtApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(dumbFHIRRestfulServer, "/dumb/*");
	}
}
