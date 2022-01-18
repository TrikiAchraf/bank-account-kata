package com.bank.account.kata.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bank.account.kata.domain.BankAccount;
import com.bank.account.kata.domain.Client;
import com.bank.account.kata.domain.Operation;
import com.bank.account.kata.enums.OperationType;

@DataJpaTest
class OperationRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private OperationRepository operationRepository;

    private static Client client;
    private static BankAccount bankAccount;
    private static Operation operation;


    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Lafont");

        bankAccount = new BankAccount();
        bankAccount.setBalance(1000d);

        operation = new Operation(OperationType.DEPOSIT, 100d);
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.bankAccountRepository.save(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 1000d);

        operation.setBankAccount(savedBankAccount);
        Operation savedOperation = this.operationRepository.save(operation);
        assertEquals(savedOperation.getType(), OperationType.DEPOSIT);
        assertEquals(savedOperation.getAmount(), 100d);
    }
}