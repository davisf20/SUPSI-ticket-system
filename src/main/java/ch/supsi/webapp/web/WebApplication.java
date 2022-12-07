package ch.supsi.webapp.web;

import ch.supsi.webapp.web.model.Role;
import ch.supsi.webapp.web.model.Status;
import ch.supsi.webapp.web.model.Type;
import ch.supsi.webapp.web.model.User;
import ch.supsi.webapp.web.service.RoleService;
import ch.supsi.webapp.web.service.StatusService;
import ch.supsi.webapp.web.service.TypeService;
import ch.supsi.webapp.web.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	@Bean
	public CommandLineRunner addTypes(TypeService typeService) {
		return args -> {
			if (typeService.getAll().size() == 0) {
				String[] names = {"Task", "Story", "Issue", "Bug", "Investigation"};
				for (String name : names) {
					Type type = new Type();
					type.setName(name);
					typeService.save(type);
				}
			}
		};
	}

	@Bean
	public CommandLineRunner addRoles(RoleService roleService) {
		return args -> {
			if (roleService.getAll().size() == 0) {
				String[] names = {"ROLE_ADMIN", "ROLE_USER"};
				for (String name : names) {
					Role role = new Role();
					role.setName(name);
					roleService.save(role);
				}
			}
		};
	}

	@Bean
	public CommandLineRunner addUsers(UserService userService, RoleService roleService) {
		return args -> { // TODO: fix default users, they cannot login
			if (userService.getByUsername("admin") == null) {
				User user = new User();
				user.setUsername("user");
				user.setPassword(new BCryptPasswordEncoder().encode("user"));
				user.setRole(roleService.get(1));
				userService.save(user);

				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
				admin.setRole(roleService.get(0));
				userService.save(admin);
			}
		};
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
