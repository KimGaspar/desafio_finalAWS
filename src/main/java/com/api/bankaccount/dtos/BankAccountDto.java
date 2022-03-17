package com.api.bankaccount.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BankAccountDto {

	//Dados de entrada no banco de dados
	@NotBlank
	@Size(max = 50)
	private String nameOwner;
	
	@NotBlank
	@Size(max = 4)
	private String agencyCode;
	
	@NotBlank
	@Size(max = 8)
	private String numberAccount;
	
	@NotBlank
	@Size(max = 3)
	private String digitVerification;
	
	@NotBlank
	@Size(max = 20)
	private String registerId;

	
	
	//Get and Setters
	public String getNameOwner() {
		return nameOwner;
	}

	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public String getDigitVerification() {
		return digitVerification;
	}

	public void setDigitVerification(String digitVerification) {
		this.digitVerification = digitVerification;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	
	
}
