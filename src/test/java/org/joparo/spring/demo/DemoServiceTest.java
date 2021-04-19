package org.joparo.spring.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoServiceTest {

    @Autowired
    DemoRepository repo;

    @Autowired
    DemoService service;

    @BeforeEach
    public void clear() {
        repo.deleteAll();
    }

    @Test
    public void testTransactionRollback() {
        assertTrue(repo.findAll().isEmpty());
        try {
            service.sparaDemoTransactionalException();
        } catch (RuntimeException e) {
            assertTrue(repo.findAll().isEmpty());
        }
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    public void testTransactionNoRollback() {
        assertTrue(repo.findAll().isEmpty());
        try {
            service.sparaDemoNonTransactionalException();
        } catch (RuntimeException e) {
            assertFalse(repo.findAll().isEmpty());
        }
        assertFalse(repo.findAll().isEmpty());
    }

}
