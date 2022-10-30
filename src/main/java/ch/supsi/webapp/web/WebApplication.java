package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.service.StatusService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public CommandLineRunner addStatuses(StatusService statusService) {
		return args -> {
			if (statusService.getAll().size() == 0) {
				String[] names = {"Open", "In progress", "Done", "Closed"};
				for (String name : names) {
					Status status = new Status();
					status.setName(name);
					statusService.save(status);
				}
			}
		};
	}
}
