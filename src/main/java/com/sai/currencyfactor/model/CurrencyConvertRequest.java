package com.sai.currencyfactor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConvertRequest {
	public String countryCode;
	public double amount;
}
