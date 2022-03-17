package com.api.bankaccount.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.bankaccount.models.BankAccountModel;
import com.api.bankaccount.repositories.BankAccountRepository;


@Service
public class BankAccountService {

	
	final BankAccountRepository bankAccountRepository;
	
	//Construtor
	public BankAccountService(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	//metodo save 
	@Transactional
	public BankAccountModel save(BankAccountModel bankAccountModel) {
		
		return bankAccountRepository.save(bankAccountModel);
	}

	public boolean existsByNameOwner(String nameOwner) {
		
		return bankAccountRepository.existsByNameOwner(nameOwner);
	}

	public boolean existsByAgencyCode(String agencyCode) {
		
		return bankAccountRepository.existsByAgencyCode(agencyCode);
	}

	public boolean existsByNumberAccount(String numberAccount) {
		
		return bankAccountRepository.existsByNumberAccount(numberAccount);
	}

	public boolean existsByDigitVerification(String digitVerification) {
		
		return bankAccountRepository.existsByDigitVerification(digitVerification);
	}

	public boolean existsByRegisterId(String registerId) {
		
		return bankAccountRepository.existsByRegisterId(registerId);
	}


	public List<BankAccountModel> findAll() {
		
		return bankAccountRepository.findAll();
	}

	public Optional<BankAccountModel> findById(UUID id) {
		
		return bankAccountRepository.findById(id);
	}

	@Transactional
	public void delete(BankAccountModel bankAccountModel) {
		
		bankAccountRepository.delete(bankAccountModel);
		
	}

	
	
}
