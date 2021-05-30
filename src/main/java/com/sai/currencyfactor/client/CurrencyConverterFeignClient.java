package com.sai.currencyfactor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sai.currencyfactor.model.CurrencyConvertRequest;

@FeignClient(name="CurrencyConversion-service",path = "currency")
public interface CurrencyConverterFeignClient {
	@PostMapping("convert")
	public double currencyConverter(@RequestBody CurrencyConvertRequest ccr);

}
