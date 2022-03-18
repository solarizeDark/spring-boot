package ru.fedusiv;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fedusiv.dto.Bio;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    final String url = "http://localhost:8080/students";

    private Bio bio = Bio.builder()
            .name("Egor")
            .surname("Fedusiv")
            .age(20)
            .group("a0001")
            .build();

    @Test
    void studentSaveNameSurnameNonUniqueError() {

        HttpEntity<Bio> request = new HttpEntity<>(bio);
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, request, String.class);

        assert(response.getBody().equals("Error: student with such name and surnames already exists!"));

    }

    @Test
    void studentSaveNameError() {

        bio.setName("egor");

        HttpEntity<Bio> request = new HttpEntity<>(bio);
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, request, String.class);

        assert(response.getBody().equals("Error: incorrect name or surname!"));

    }

    @Test
    void studentSaveSurnameError() {

        bio.setName("fedusiv");

        HttpEntity<Bio> request = new HttpEntity<>(bio);
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, request, String.class);

        assert(response.getBody().equals("Error: incorrect name or surname!"));

    }

    @Test
    void studentSaveAgeHighError() {

        bio.setAge(228);

        HttpEntity<Bio> request = new HttpEntity<>(bio);
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, request, String.class);

        assert(response.getBody().equals("Error: incorrect age!"));

    }

    @Test
    void studentSaveAgeLowError() {

        bio.setAge(-5);

        HttpEntity<Bio> request = new HttpEntity<>(bio);
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, request, String.class);

        assert(response.getBody().equals("Error: incorrect age!"));

    }

}
