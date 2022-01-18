package com.bank.account.kata.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bank.account.kata.domain.BankAccount;
import com.bank.account.kata.domain.Client;

@DataJpaTest
class BankAccountRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private static Client client;
    private static BankAccount bankAccount;


    @BeforeEach
    void setUp() {
        client = new Client();
        client.setLastName("Lafont");

        bankAccount = new BankAccount();
        bankAccount.setBalance(1000d);
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        bankAccount.setClient(savedClient);
        BankAccount savedBankAccount = this.bankAccountRepository.save(bankAccount);
        assertEquals(savedBankAccount.getBalance(), 1000d);
    }
}