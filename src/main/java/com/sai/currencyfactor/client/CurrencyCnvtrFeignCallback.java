package com.sai.currencyfactor.client;

import org.springframework.stereotype.Component;

import com.sai.currencyfactor.model.CurrencyConvertRequest;

@Component
public class CurrencyCnvtrFeignCallback implements CurrencyConverterFeignClient{
	

	@Override
	public double currencyConverter(CurrencyConvertRequest ccr) {
		// TODO Auto-generated method stub
		return 0.000;
	}
}
