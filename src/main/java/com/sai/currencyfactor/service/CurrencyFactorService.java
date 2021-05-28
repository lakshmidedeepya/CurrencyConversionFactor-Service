package com.sai.currencyfactor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.currencyfactor.model.CurrencyFactorRequest;
import com.sai.currencyfactor.repo.CurrencyFactorRepo;

@Service
public class CurrencyFactorService {

	@Autowired
	public CurrencyFactorRepo repo;
	
	public void addCurrencyConvtnFctr(CurrencyFactorRequest cfreq) {
		// TODO Auto-generated method stub
		repo.save(cfreq);
	}

	public double getCurrencyFactor(String cc) {
		// TODO Auto-generated method stub
		return repo.findById(cc).get().conversionFactor;
	}

	
	
	
}
