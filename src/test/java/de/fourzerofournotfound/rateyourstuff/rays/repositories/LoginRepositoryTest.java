package de.fourzerofournotfound.rateyourstuff.rays.repositories;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties = "spring.profiles.active = test")

class LoginRepositoryTest {
    @Autowired
    LoginRepository loginRepository;

    @AfterEach
    public void afterEach() {loginRepository.deleteAll();};


}