package com.api.bankaccount.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.bankaccount.dtos.BankAccountDto;
import com.api.bankaccount.models.BankAccountModel;
import com.api.bankaccount.services.BankAccountService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bank-account")
public class BankAccountController {

	final BankAccountService bankAccountService;

	//Construtor
	public BankAccountController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	//Método Post
	@PostMapping
	public ResponseEntity<Object> saveBankAccount(@RequestBody @Valid BankAccountDto bankAccountDto){
		//Verificação de cadastro no banco
		if(bankAccountService.existsByNameOwner(bankAccountDto.getNameOwner())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The name is already in use!");
		}
		
		if(bankAccountService.existsByAgencyCode(bankAccountDto.getAgencyCode())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The agency is already in use!");
		}
		if(bankAccountService.existsByNumberAccount(bankAccountDto.getNumberAccount())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The number account is already in use!");
		}
		if(bankAccountService.existsByDigitVerification(bankAccountDto.getDigitVerification())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The digit verification is already in use!");
		}
		if(bankAccountService.existsByRegisterId(bankAccountDto.getRegisterId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The register is already in use!");
		}
		
		var bankAccountModel = new BankAccountModel();
		BeanUtils.copyProperties(bankAccountDto, bankAccountModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.save(bankAccountModel));
	}
	
	
	//Metodo GetAll
	@GetMapping
	public ResponseEntity<List<BankAccountModel>> getAllBankAccounts(){
		return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.findAll());
	}
	
	//Metodo GetOne
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneBankAccount(@PathVariable(value = "id") UUID id){
		Optional<BankAccountModel> bankAccountModelOptional = bankAccountService.findById(id);
		if(!bankAccountModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(bankAccountModelOptional.get());
	}
	
	//Metodo Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBankAccount(@PathVariable(value = "id") UUID id){
		Optional<BankAccountModel> bankAccountModelOptional = bankAccountService.findById(id);
		if(!bankAccountModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found.");
		}
		bankAccountService.delete(bankAccountModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Bank Account deleted successfully.");
	}
	
	//Metodo Put
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBankAccount(@PathVariable(value = "id") UUID id, @RequestBody @Valid BankAccountDto bankAccountDto){
		Optional<BankAccountModel> bankAccountModelOptional =  bankAccountService.findById(id);
		if(!bankAccountModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank Account not found.");
		}
		var bankAccountModel = new BankAccountModel();
		BeanUtils.copyProperties(bankAccountDto, bankAccountModel);
		bankAccountModel.setId(bankAccountModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.save(bankAccountModel));
	}
	
}
