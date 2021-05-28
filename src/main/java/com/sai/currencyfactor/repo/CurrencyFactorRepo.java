package com.sai.currencyfactor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sai.currencyfactor.model.CurrencyFactorRequest;

@Repository
public interface CurrencyFactorRepo extends JpaRepository<CurrencyFactorRequest, String>{

}
