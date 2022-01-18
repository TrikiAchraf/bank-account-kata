package com.bank.account.kata.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.bank.account.kata.domain.BankAccount;
import com.bank.account.kata.domain.Client;
import com.bank.account.kata.domain.Operation;
import com.bank.account.kata.enums.OperationType;

@DataJpaTest
class OperationTest {

    @Autowired
    private TestEntityManager entityManager;

    private BankAccount bankAccount;
    private Client client;
    private Operation operation;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setLastName("Lafont");

        bankAccount = new BankAccount();

        operation = new Operation(OperationType.DEPOSIT, 100d);
    }

    @Test
    public void saveOperation() {
        Client savedClient = this.entityManager.persistAndFlush(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.entityManager.persistAndFlush(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 0d);

        operation.setBankAccount(savedBankAccount);
        Operation savedOperation = this.entityManager.persistAndFlush(operation);
        assertEquals(savedOperation.getType(), OperationType.DEPOSIT);
        assertEquals(savedOperation.getAmount(), 100d);
    }


}