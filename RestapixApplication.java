package AshishProject.Supemarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("AshishProject")
@EntityScan("AshishProject")
public class RestapixApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapixApplication.class, args);
	}

}
