package com.kakaopaysec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KakaopaysecApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KakaopaysecApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(KakaopaysecApplication.class, args);
	}

}
