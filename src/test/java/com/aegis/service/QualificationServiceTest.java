package com.aegis.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.aegis.entity.Qualification;
import com.aegis.repository.QualificationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class QualificationServiceTest {

    private static Qualification q1;
    private static Qualification q2;

    @Mock
    private QualificationRepository qualificationRepository;

    @InjectMocks
    private QualificationService qualificationService;

    @BeforeAll
    public static void init() {
        q1 = new Qualification("Q1");
        q2 = new Qualification("Q2");
    }

    @Test
    public void findAllTest_WhenNoRecord() {

       Mockito.when(qualificationRepository.findAll()).thenReturn(Arrays.asList());
       assertThat(qualificationService.findAll().size(), is(0));
       Mockito.verify(qualificationRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecord() {

        Mockito.when(qualificationRepository.findAll()).thenReturn(Arrays.asList(q1, q2));
        assertThat(qualificationService.findAll().size(), is(2));
        assertThat(qualificationService.findAll().get(0), is(q1));
        assertThat(qualificationService.findAll().get(1),is(q2));
        Mockito.verify(qualificationRepository, Mockito.times(3)).findAll();

    }

    @Test
    public void findById() {

        Mockito.when(qualificationRepository.findById(1L)).thenReturn(Optional.of(q1));
        assertThat(qualificationService.findById(1L), is(Optional.of(q1)));
        Mockito.verify(qualificationRepository, Mockito.times(1)).findById(1L);
    }


    @Test
    void createOrUpdate() {

        Mockito.when(qualificationRepository.save(q1)).thenReturn(q1);
        assertThat(qualificationService.createOrUpdate(q1), is(q1));
        Mockito.verify(qualificationRepository, Mockito.times(1)).save(q1);

        Mockito.when(qualificationRepository.save(q2)).thenReturn(q2);
        assertThat(qualificationService.createOrUpdate(q2).getName(), is("Q2"));
        Mockito.verify(qualificationRepository, Mockito.times(1)).save(q2);
    }

    @Test
    void deleteById() {
        qualificationService.deleteById(1L);
        Mockito.verify(qualificationRepository, Mockito.times(1)).deleteById(1L);
    }
}