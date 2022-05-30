package com.elaamiri.ebankbackend.repositories;

import com.elaamiri.ebankbackend.entities.AccountOperation;
import com.elaamiri.ebankbackend.entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
     Page<AccountOperation> getAccountOperationsByBankAccount(BankAccount bankAccount, Pageable pageable);
     Page<AccountOperation> getAccountOperationsByBankAccountOrderByDateDesc(BankAccount bankAccount, Pageable pageable);
}
