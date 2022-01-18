package com.bank.account.kata.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.bank.account.kata.domain.Client;


@DataJpaTest
class ClientTest {

    @Autowired
    private TestEntityManager entityManager;

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setLastName("Lafont");
    }

    @Test
    public void saveClient() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        assertEquals(savedClient.getLastName(), "Lafont");
    }

}