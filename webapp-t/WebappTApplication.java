package ru.fedusiv.webappt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/*

    added for testing

 */

@SpringBootApplication
public class WebappTApplication {

    static String jsonBody = "{ \"name\": \"Petr\", \"surname\": \"Petroff\", \"age\": \"20\", \"group\": \"a0001\" }";

    public static void main(String[] args) {
        SpringApplication.run(WebappTApplication.class, args);
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Origin", "webapp-t");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        String url = "http://localhost:8080/students/add";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        System.out.println(response.getStatusCode() + "\n" + response.getBody());

    }

}
