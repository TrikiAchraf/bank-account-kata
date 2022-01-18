package com.bank.account.kata.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bank.account.kata.dtos.OperationDto;
import com.bank.account.kata.dtos.OperationRequestBody;
import com.bank.account.kata.exceptions.BankAccountNotFoundException;
import com.bank.account.kata.exceptions.ClientNotFoundException;
import com.bank.account.kata.services.OperationService;


@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    
    @GetMapping(value="/operations")
    public List<OperationDto> getAllOperations() {
        return operationService.allOperations();
    }

   
    @GetMapping(value="/balance")
    public Double getBalance() throws BankAccountNotFoundException {
        return operationService.getBalance();
    }

   
    @PostMapping(value = "/deposit")
    public ResponseEntity<OperationDto> deposit(@RequestBody OperationRequestBody operation) throws BankAccountNotFoundException {
        OperationDto newOperation = operationService.deposit(operation.getAmount());

        if(newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

    
    @PostMapping(value = "/withdraw")
    public ResponseEntity<OperationDto> withdraw(@RequestBody OperationRequestBody operation) throws BankAccountNotFoundException {
        OperationDto newOperation = operationService.withdraw(operation.getAmount());

        if(newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

    
    @ExceptionHandler({ BankAccountNotFoundException.class , ClientNotFoundException.class })
    public ResponseEntity<Object> handleObjectNotFoundException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Object not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}