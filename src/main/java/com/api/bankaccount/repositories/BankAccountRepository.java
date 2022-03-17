package com.api.bankaccount.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.bankaccount.models.BankAccountModel;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountModel, UUID> {

	boolean existsByNameOwner(String nameOwner);
	boolean existsByAgencyCode(String agencyCode);
	boolean existsByNumberAccount(String numberAccount);
	boolean existsByDigitVerification(String digitVerification);
	boolean existsByRegisterId(String registerId);
	
	
}
