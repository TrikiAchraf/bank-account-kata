package com.bank.account.kata.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bank.account.kata.domain.BankAccount;
import com.bank.account.kata.domain.Operation;
import com.bank.account.kata.dtos.OperationDto;
import com.bank.account.kata.exceptions.BankAccountNotFoundException;
import com.bank.account.kata.repositories.BankAccountRepository;
import com.bank.account.kata.repositories.OperationRepository;
import com.bank.account.kata.services.impl.OperationServiceImpl;

@SpringBootTest
class OperationServiceTest {

    OperationServiceImpl operationService;

    @MockBean
    OperationRepository operationRepository;

    @MockBean
    BankAccountRepository bankAccountRepository;
    
    private BankAccount bankAccount = buildBankAccount();
    
    private Operation operation = buildOperation();

    @BeforeEach
    void setUp() {
    	
    	operationService = new OperationServiceImpl();

        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenReturn(bankAccount);

        when(operationRepository.save(any(Operation.class)))
                .thenReturn(operation);

    }

    @Test
    void deposit() throws BankAccountNotFoundException {
        OperationDto deposit = operationService.deposit(100d);
        assertNotNull(deposit);
        assertEquals(deposit.getBankAccountBalance(), 1100d);
    }

    @Test
    void withdraw() throws BankAccountNotFoundException {
        OperationDto withdraw = operationService.withdraw(100d);
        assertNotNull(withdraw);
        assertEquals(withdraw.getBankAccountBalance(), 900d);
    }
    
    private BankAccount buildBankAccount() {
    	 BankAccount bankAccount = new BankAccount();
    	 bankAccount.setBalance(1000d);
    	 return bankAccount;
    }
    
    private Operation buildOperation() {
    	 Operation operation = new Operation();
    	 operation.setBankAccount(this.bankAccount);
    	 return operation;
    }
   
}