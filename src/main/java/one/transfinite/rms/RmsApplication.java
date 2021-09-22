package one.transfinite.rms;

import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
import one.transfinite.rms.user.UserService;
import one.transfinite.rms.utility.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RmsApplication  implements CommandLineRunner{

	@Autowired
	private UserService userService;

	public static void main(String[] args)
	{
		SpringApplication.run(RmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started");

		Role role = new Role();
		role.setName(RoleName.CUSTOMER.name());

		User user = new User();
		user.setName("customer");
		user.setEmail("customer@gmail.com");
		user.setPhone("7485963215");
		user.setPassword("password");
		user.setRole(role);

		userService.addUser(user);

		Role role1 = new Role();
		role1.setName(RoleName.ADMIN.name());

		User user1 = new User();
		user1.setName("admin");
		user1.setEmail("admin@gmail.com");
		user1.setPhone("7485957215");
		user1.setPassword("password");
		user1.setRole(role1);

		userService.addUser(user1);

		Role role2 = new Role();
		role2.setName(RoleName.VENDOR.name());

		User user2 = new User();
		user2.setName("vendor");
		user2.setEmail("vendor@gmail.com");
		user2.setPhone("7487363215");
		user2.setPassword("password");
		user2.setRole(role2);

		userService.addUser(user2);
	}
}
