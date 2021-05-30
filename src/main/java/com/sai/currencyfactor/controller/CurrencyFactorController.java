package com.sai.currencyfactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sai.currencyfactor.model.CurrencyConvertRequest;
import com.sai.currencyfactor.model.CurrencyFactorRequest;
import com.sai.currencyfactor.model.CurrencyFactorResponse;
import com.sai.currencyfactor.service.CurrencyFactorService;

@RestController
@RequestMapping("/currencyfactor")
public class CurrencyFactorController {

	@Autowired
	public CurrencyFactorService service;
	@PostMapping("/add")
	public ResponseEntity<CurrencyFactorResponse> addConversionFactor(@RequestBody CurrencyFactorRequest cfreq){
		CurrencyFactorResponse cres=new CurrencyFactorResponse();
		
		try {
			service.addCurrencyConvtnFctr(cfreq);
			cres.message="Currency factor saved";
			return new ResponseEntity<CurrencyFactorResponse>(cres,HttpStatus.CREATED);
		}catch(Exception e) {
			cres.message="Currency factor save failed due to exception";
			return new ResponseEntity<CurrencyFactorResponse>(cres,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update")
	public ResponseEntity<CurrencyFactorResponse> updateConversionFactor(@RequestBody CurrencyFactorRequest cfreq){
		CurrencyFactorResponse cres=new CurrencyFactorResponse();
		
		try {
			service.addCurrencyConvtnFctr(cfreq);
			cres.message="Currency factor updated";
			return new ResponseEntity<CurrencyFactorResponse>(cres,HttpStatus.OK);
		}catch(Exception e) {
			cres.message="Currency factor update failed due to exception";
			return new ResponseEntity<CurrencyFactorResponse>(cres,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("{countrycode}")
	public ResponseEntity<Double> getConversionFactor(@PathVariable(name="countrycode") String cc){
		try {
			double currfacr=service.getCurrencyFactor(cc);
			
			return new ResponseEntity<Double>(currfacr,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
	@PostMapping("/convertCF")
	public ResponseEntity<Double> convertCurrencyFactor(@RequestBody CurrencyConvertRequest ccr) {
		try {
			double currfacr=service.convertAndGetCurrencyFactor(ccr);
			
			return new ResponseEntity<Double>(currfacr,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		//return cs.convertCurrency(ccr);
	}
}
