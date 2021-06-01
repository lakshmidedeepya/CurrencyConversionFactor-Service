package com.sai.currencyfactor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@Autowired
	RestTemplate resttemplate;
	public void addCurrencyConvtnFctr(CurrencyFactorRequest cfreq) {
		// TODO Auto-generated method stub
		repo.save(cfreq);
	}

	public double getCurrencyFactor(String cc) {
		// TODO Auto-generated method stub
		return repo.findById(cc).get().conversionFactor;
	}

	//using feign
		public double convertAndGetCurrencyFactor(CurrencyConvertRequest ccr) {
			// TODO Auto-generated method stub
			return feign.currencyConverter(ccr);
			
		}
		//using discoveryClient 
		public double convertAndGetCurrencyFactorV1(CurrencyConvertRequest ccr) {
			
			List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CurrencyConversion-service");
			if (serviceInstances != null && serviceInstances.size() > 0) {
				for (ServiceInstance instance : serviceInstances) {
					System.out.println(instance.getHost() + ":" + instance.getPort());
				}
				ServiceInstance instance = serviceInstances.get(0);
				String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/currency/convert";
				System.out.println("Calling :" + url);
				RestTemplate restTemplate = new RestTemplate();
				HttpEntity<CurrencyConvertRequest> dRequest = new HttpEntity<CurrencyConvertRequest>(ccr);
				Double dResponse = restTemplate.postForEntity(url, dRequest, Double.class).getBody();
				return dResponse.doubleValue();
			
			}else return 0.00;

			}
		//using hystrix
		@HystrixCommand(fallbackMethod = "defaultConversion")
		public double convertAndGetCurrencyFactorV2(CurrencyConvertRequest ccr) {
				String url = "http://CurrencyConversion-service/currency/convert";
				HttpEntity<CurrencyConvertRequest> dRequest = new HttpEntity<CurrencyConvertRequest>(ccr);
				Double dResponse = resttemplate.postForEntity(url, dRequest, Double.class).getBody();
				return dResponse.doubleValue();
			}
		public double defaultConversion(CurrencyConvertRequest ccr) {
			return 0.00;
		}
}
