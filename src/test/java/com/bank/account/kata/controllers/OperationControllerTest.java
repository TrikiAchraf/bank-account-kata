package com.bank.account.kata.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bank.account.kata.dtos.OperationDto;
import com.bank.account.kata.enums.OperationType;
import com.bank.account.kata.exceptions.BankAccountNotFoundException;
import com.bank.account.kata.services.impl.OperationServiceImpl;

@WebMvcTest(OperationController.class)
class OperationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationServiceImpl operationService;

    @BeforeEach
    void setUp() throws BankAccountNotFoundException {
        OperationDto deposit = new OperationDto();
        deposit.setBankAccountClientLastName("Lafont");
        deposit.setBankAccountBalance(100d);
        deposit.setType(OperationType.DEPOSIT);
        deposit.setAmount(100d);
        deposit.setDate(LocalDateTime.now());

        when(operationService.deposit(100d))
                .thenReturn(deposit);

        OperationDto withdraw = new OperationDto();
        withdraw.setBankAccountClientLastName("Lafont");
        withdraw.setBankAccountBalance(0d);
        withdraw.setType(OperationType.WITHDRAWAL);
        withdraw.setAmount(100d);
        withdraw.setDate(LocalDateTime.now());
        when(operationService.withdraw(100d))
                .thenReturn(withdraw);
    }

    @Test
    public void allOperations() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/operations")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deposit() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/deposit")
                .content("{\"amount\":100}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(100));
    }

    @Test
    public void withdraw() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/withdraw")
                .content("{\"amount\":100}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0));
    }

}