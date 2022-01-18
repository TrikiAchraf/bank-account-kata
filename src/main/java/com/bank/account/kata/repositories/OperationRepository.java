package com.bank.account.kata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.kata.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
