package com.bank.account.kata.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.bank.account.kata.domain.BankAccount;
import com.bank.account.kata.domain.Client;


@DataJpaTest
class BankAccountTest {

    @Autowired
    private TestEntityManager entityManager;

    private BankAccount bankAccount;
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setLastName("Lafont");

        bankAccount = new BankAccount();
        bankAccount.setBalance(1000d);
    }

    @Test
    public void saveClient() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 1000d);
    }

    

}