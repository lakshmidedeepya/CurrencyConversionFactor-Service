package com.sai.currencyfactor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.currencyfactor.client.CurrencyConverterFeignClient;
import com.sai.currencyfactor.model.CurrencyConvertRequest;
import com.sai.currencyfactor.model.CurrencyFactorRequest;
import com.sai.currencyfactor.repo.CurrencyFactorRepo;

@Service
public class CurrencyFactorService {

	@Autowired
	public CurrencyFactorRepo repo;
	
	@Autowired
	public CurrencyConverterFeignClient feign;
	
	public void addCurrencyConvtnFctr(CurrencyFactorRequest cfreq) {
		// TODO Auto-generated method stub
		repo.save(cfreq);
	}

	public double getCurrencyFactor(String cc) {
		// TODO Auto-generated method stub
		return repo.findById(cc).get().conversionFactor;
	}

	public double convertAndGetCurrencyFactor(CurrencyConvertRequest ccr) {
		// TODO Auto-generated method stub
		return feign.currencyConverter(ccr);
		
	}

	
	
	
}
