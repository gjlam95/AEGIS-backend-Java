package com.aegis.controller;

import com.aegis.entity.Qualification;
import com.aegis.service.QualificationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class QualificationControllerTest {

    private static Qualification q1;
    private static Qualification q2;
    private static Qualification q3;

    @Mock
    private QualificationService productService;

    @InjectMocks
    private QualificationController productController;

    @BeforeAll
    public static void init() {
        q1 = new Qualification("Q1", true);
        q2 = new Qualification("Q2", true);
        q3 = new Qualification("Q3", false);

    }

    @Test
    void findAll_whenNoRecord() {

        Mockito.when(productService.findAll()).thenReturn(Arrays.asList());
        assertThat(productController.findAll().size(), is(0));
        Mockito.verify(productService, Mockito.times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {

        Mockito.when(productService.findAll()).thenReturn(Arrays.asList(q1, q2));
        assertThat(productController.findAll().size(), is(2));
        Mockito.verify(productService, Mockito.times(1)).findAll();
    }

    @Test
    void create() {

        ResponseEntity<Qualification> p = productController.create(q1);
        Mockito.verify(productService, Mockito.times(1)).createOrUpdate(q1);
    }

    @Test
    void findById_WhenMatch() {

        Mockito.when(productService.findById(1L)).thenReturn(Optional.of(q1));
        ResponseEntity<Qualification> q = productController.findById(1L);
        assertThat(q.getBody(), is(q1) );
    }

    @Test
    void findById_WhenNoMatch() {

        Mockito.when(productService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Qualification> p = productController.findById(1L);
        assertThat(p.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    void update_WhenNotFound() {

        Mockito.when(productService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Qualification> p = productController.update(1L, q1);
        assertThat(p.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    void update_WhenFound() {

        Mockito.when(productService.findById(1L)).thenReturn(Optional.of(q1));

        //Since the Controller internally saves q1 taking args of q3.
        Mockito.when(productService.createOrUpdate(q1)).thenReturn(q3);
        assertThat(productController.update(1L, q3).getBody().getName(), is("P3"));
        Mockito.verify(productService, Mockito.times(1)).createOrUpdate(q1);
    }

    @Test
    void deleteById_WhenNotFound() {
        Mockito.when(productService.findById(1L)).thenReturn(Optional.empty());
        productController.deleteById(1L);
        Mockito.verify(productService, Mockito.times(0)).deleteById(1L);
    }

    @Test
    void deleteById_WhenFound() {
        Mockito.when(productService.findById(1L)).thenReturn(Optional.of(q1));
        productController.deleteById(1L);
        Mockito.verify(productService, Mockito.times(1)).deleteById(1L);
    }
}