package jpa.imform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ImformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImformApplication.class, args);
	}

}
