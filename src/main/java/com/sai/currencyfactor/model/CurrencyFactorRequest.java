package com.sai.currencyfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="CurrencyConversion")
public class CurrencyFactorRequest {
	
	@Id
	public String countrycode;
	@Column
	public double conversionFactor;
	
}
