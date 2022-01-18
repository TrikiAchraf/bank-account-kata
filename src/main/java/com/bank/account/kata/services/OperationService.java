package com.bank.account.kata.services;

import java.util.List;

import com.bank.account.kata.dtos.OperationDto;
import com.bank.account.kata.exceptions.BankAccountNotFoundException;


public interface OperationService {

    OperationDto deposit(Double amount) throws BankAccountNotFoundException;

    OperationDto withdraw(Double amount) throws BankAccountNotFoundException;

    List<OperationDto> allOperations();

    Double getBalance() throws BankAccountNotFoundException;
}
