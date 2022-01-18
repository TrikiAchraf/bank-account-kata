package com.bank.account.kata.dtos;

import java.time.LocalDateTime;

import com.bank.account.kata.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationDto {
    private Long id;

    private OperationType type;

    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @JsonProperty("balance")
    private Double bankAccountBalance;

    @JsonProperty("client")
    private String bankAccountClientLastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(Double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public String getBankAccountClientLastName() {
        return bankAccountClientLastName;
    }

    public void setBankAccountClientLastName(String bankAccountClientLastName) {
        this.bankAccountClientLastName = bankAccountClientLastName;
    }
}
