package com.bank.account.kata.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.kata.domain.BankAccount;
import com.bank.account.kata.domain.Operation;
import com.bank.account.kata.dtos.OperationDto;
import com.bank.account.kata.enums.OperationType;
import com.bank.account.kata.exceptions.BankAccountNotFoundException;
import com.bank.account.kata.repositories.BankAccountRepository;
import com.bank.account.kata.repositories.OperationRepository;
import com.bank.account.kata.services.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OperationDto deposit(Double amount) throws BankAccountNotFoundException {
        Operation operation = new Operation(OperationType.DEPOSIT, amount);

        BankAccount bankAccount = null;
    	if (bankAccountRepository.findById(1L).isPresent() ) {
    		 bankAccount = bankAccountRepository.findById(1L).get();
    	} else {
    		throw new BankAccountNotFoundException("Bank account not found !");
    	}
        operation.setBankAccount(bankAccount);

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
        return modelMapper.map(operationRepository.save(operation), OperationDto.class);
    }

    @Override
    public OperationDto withdraw(Double amount) throws BankAccountNotFoundException {
        amount = amount > 0 ? (-1 * amount) : amount;

        Operation operation = new Operation(OperationType.WITHDRAWAL, amount);

        BankAccount bankAccount = null;
    	if (bankAccountRepository.findById(1L).isPresent() ) {
    		 bankAccount = bankAccountRepository.findById(1L).get();
    	} else {
    		throw new BankAccountNotFoundException("Bank account not found !");
    	}
        operation.setBankAccount(bankAccount);

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
        return modelMapper.map(operationRepository.save(operation), OperationDto.class);
    }

    @Override
    public List<OperationDto> allOperations() {
        List<Operation> operations = operationRepository.findAll();
        return operations
                .stream()
                .map(operation -> modelMapper.map(operation, OperationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double getBalance() throws BankAccountNotFoundException {
    	BankAccount bankAccount = null;
    	if (bankAccountRepository.findById(1L).isPresent() ) {
    		 bankAccount = bankAccountRepository.findById(1L).get();
    	} else {
    		throw new BankAccountNotFoundException("Bank account not found !");
    	}
       
        return bankAccount.getBalance();
    }
}
