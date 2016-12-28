package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

	@Autowired
	UserRepository repository;

	@Test
	public void test() {
		User superUser = new User("super", "user");

		User managingUser = new User("managing", "user", superUser);
		superUser.addChild(managingUser);

		User regularUser = new User("regular", "user", managingUser);
		managingUser.addChild(regularUser);

		repository.save(superUser);
		repository.save(managingUser);
		repository.save(regularUser);

		Assert.assertEquals(3, repository.count());

		log.info("superUser: {}", superUser);
		log.info("managingUser: {}", managingUser);
		log.info("regularUser: {}", regularUser);

		log.info("superUser children: {}", superUser.getChildren());
	}

}
