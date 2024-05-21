package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.description;

@SpringBootTest
@Transactional
class DemoApplicationTests {
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

	@Test
	void contextLoads() {}
	@Test
	void findAllUser(){
		String uniqueLogin = "newuser121122111";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();
		User savedUser = userService.save(newUser);
		Iterable<User> testAllUser = userService.findAll();
		Assertions.assertNotNull(testAllUser);
		boolean userFound = false;
		int userCount = 0;
		for (User user : testAllUser) {
			if (user.getId().equals(savedUser.getId())) {
				userFound = true;
			}
			userCount++;
		}

		Assertions.assertTrue(userFound);
		Assertions.assertTrue(userCount > 0);

		taskRepository.deleteAll();
		userRepository.deleteAll(Arrays.asList(savedUser));
	}
	@Test
	void findingLogin(){
		String uniqueLogin = "newuser121122111";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();
		User savedUser = userService.save(newUser);
		Optional<User> testUser = userService.findByLogin(savedUser.getUsername());
		Assertions.assertNotNull(testUser);
		userRepository.deleteAll();
	}
	@Test
	void saveUser() {
		String uniqueLogin = "newuser1211221";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();

		User savedUser = userService.save(newUser);
		User findUser = userService.findByLogin(savedUser.getUsername()).get();
		Assertions.assertNotNull(findUser);
		Assertions.assertEquals(savedUser.getId(), findUser.getId());
		Assertions.assertEquals(savedUser.getUsername(), findUser.getUsername());
		userRepository.deleteAll();

	}

	@Test
	void saveTask(){
		String uniqueLogin = "testerUser";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();

		User savedUser = userService.save(newUser);

		Task newTask = Task.builder()
				.date(LocalDate.parse("2021-01-01"))
				.description("Hey, I am your Description. Please, write the hello world program on java!!! ")
				.user(savedUser)
				.build();

		Task savedTask = taskService.save(newTask);
		Task findTask = taskService.findById(savedTask.getId());
		Assertions.assertNotNull(findTask);
		Assertions.assertEquals(newTask.getDescription(), savedTask.getDescription());
		Assertions.assertEquals(newTask.getDate(), savedTask.getDate());
		taskRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void deleteTaskId() {
		String uniqueLogin = "testerUser";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();

		User savedUser = userService.save(newUser);

		Task newTask = Task.builder()
				.date(LocalDate.parse("2021-01-01"))
				.description("Hey, I am your Description. Please, write the hello world program on java!!! ")
				.user(savedUser)
				.build();
		Task savedTask = taskService.save(newTask);
		Assertions.assertNotNull(savedTask);
		Assertions.assertEquals(newTask.getDescription(), savedTask.getDescription());
		Assertions.assertEquals(newTask.getDate(), savedTask.getDate());

		taskService.deleteById(savedTask.getId());

		Task deletedTask = taskService.findById(savedTask.getId());
		Assertions.assertNull(deletedTask);
		taskRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void findByIdTask(){
		String uniqueLogin = "testerUser";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();

		User savedUser = userService.save(newUser);

		Task newTask = Task.builder()
				.date(LocalDate.parse("2021-01-01"))
				.description("Hey, I am your Description. Please, write the hello world program on java!!! ")
				.user(savedUser)
				.build();
		Task savedTask = taskService.save(newTask);

		Task findTask = taskService.findById(savedTask.getId());

		Assertions.assertNotNull(findTask);
		Assertions.assertEquals(savedTask.getId(), findTask.getId());
		Assertions.assertEquals(savedTask.getDescription(), findTask.getDescription());
		Assertions.assertEquals(savedTask.getDate(), findTask.getDate());
		Assertions.assertEquals(savedTask.getUser().getId(), findTask.getUser().getId());

		taskRepository.deleteAll();
		userRepository.deleteAll();
	}


	@Test
	void findAllTasks(){
		String uniqueLogin = "testerUser";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();

		User savedUser = userService.save(newUser);

		Task newTask = Task.builder()
				.date(LocalDate.parse("2021-01-01"))
				.description("Hey, I am your Description. Please, write the hello world program on java!!! ")
				.user(savedUser)
				.build();
		Task savedTask = taskService.save(newTask);
		Iterable<Task> testAllTasks = taskService.findAll();
		Assertions.assertNotNull(testAllTasks);
		boolean taskFound = false;
		int taskCount = 0;
		for (Task task : testAllTasks) {
			if (task.getId().equals(savedTask.getId())) {
				taskFound = true;
			}
			taskCount++;
		}
		Assertions.assertTrue(taskFound);
		Assertions.assertTrue(taskCount > 0);
		taskRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void markAsDoneTask(){
		String uniqueLogin = "testerUser";
		User newUser = User.builder()
				.login(uniqueLogin)
				.password("password")
				.role(Role.USER)
				.build();

		User savedUser = userService.save(newUser);

		Task newTask = Task.builder()
				.date(LocalDate.parse("2021-01-01"))
				.description("Hey, I am your Description. Please, write the hello world program on java!!! ")
				.user(savedUser)
				.build();
		Task savedTask = taskService.save(newTask);
		taskService.markAsDone(savedTask.getId());

		Task updatedTask = taskService.findById(savedTask.getId());
		Assertions.assertTrue(updatedTask.isDone());

		taskRepository.deleteAll();
		userRepository.deleteAll();
	}



//	void markAsDone(Long id);



}
