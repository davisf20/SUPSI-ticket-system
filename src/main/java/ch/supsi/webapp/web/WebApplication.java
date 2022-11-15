package ch.supsi.webapp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner addStatuses(StatusService statusService) {
		return args -> {
			if (statusService.getAll().size() == 0) {
				String[] names = {"Open", "In progress", "Done", "Closed"};
				for (String name : names) {
					StatusEntity status = new StatusEntity();
					status.setName(name);
					statusService.save(status);
				}
			}
		};
	}*/
}
