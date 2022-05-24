package com.aegis.controller;


import com.aegis.entity.Qualification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QualificationControllerTestIT {

    private static Qualification q1;
    private static Qualification q2;
    private static Qualification q3;
    private static Qualification q4;
    private static Qualification q5;

    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        q1 = new Qualification("P1", true);
        q2 = new Qualification("P2", true);
        q3 = new Qualification("P3", false);
        q4 = new Qualification("P4", false);
        q5 = new Qualification("P5", false);
    }

    @Test
    public void findAllTestIT() {

        ResponseEntity<Qualification[]> result= this.restTemplate
                .getForEntity("http://127.0.0.1:"+port+"/qualifications/", Qualification[].class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));

        assertThat(result.getBody(), is(notNullValue()));

    }

    @Test
    public void createTestIT() {

        HttpEntity<Qualification> request = new HttpEntity<>(q1);
        ResponseEntity<Qualification> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/qualifications/", request, Qualification.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getName(), is("P1"));
        restTemplate.delete("http://127.0.0.1:"+port+"/qualifications/"+response.getBody().getId());
    }

    @Test
    public void updateTestIT() {

        HttpEntity<Qualification> request = new HttpEntity<>(q2);
        ResponseEntity<Qualification> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/qualifications/", request, Qualification.class);

        long id = response.getBody().getId();

        request = new HttpEntity<Qualification>(q3);
        response = restTemplate.exchange("http://127.0.0.1:"+port+"/qualifications/"+id, HttpMethod.PUT, request, Qualification.class);

        assertThat(response.getBody().getName(), is("P3"));
        assertThat(response.getBody().getId(), is(id));
        restTemplate.delete("http://127.0.0.1:"+port+"/qualifications/"+id);
    }

    @Test
    public void findByIDTestIT() {

        HttpEntity<Qualification> request = new HttpEntity<>(q4);
        ResponseEntity<Qualification> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/qualifications/", request, Qualification.class);

        long id = response.getBody().getId();

        response = restTemplate.getForEntity("http://127.0.0.1:"+port+"/qualifications/"+id, Qualification.class);

        assertThat(response.getBody().getId(), is(id));
        restTemplate.delete("http://127.0.0.1:"+port+"/qualifications/"+id);
    }

    @Test
    public void deleteByIDTestIT() {

        HttpEntity<Qualification> request = new HttpEntity<>(q5);
        ResponseEntity<Qualification> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/qualifications/", request, Qualification.class);

        long id = response.getBody().getId();

        restTemplate.delete("http://127.0.0.1:"+port+"/qualifications/"+id);
        response = restTemplate.getForEntity("http://127.0.0.1:"+port+"/qualifications/"+id, Qualification.class);

        assertThat(response.getBody(), is(nullValue()));

    }


}
