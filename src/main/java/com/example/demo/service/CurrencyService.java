package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Currency;
import com.example.demo.repo.CurrencyRepo;

 
@Service
public class CurrencyService {
 
	@Autowired
    private final CurrencyRepo repo;
 
    public CurrencyService(CurrencyRepo repo){
        this.repo = repo;
    }
 
    public List<Currency> getAll(){
        return repo.findAll();
    }
 
    public Currency add(Currency c){
        return repo.save(c);
    }
 
    public Currency update(Long id,Currency c){
    	
 
        Currency existing = repo.findById(id).orElseThrow();
 
        existing.setCountry(c.getCountry());
        existing.setCode(c.getCode());
        existing.setRate(c.getRate());
 
        return repo.save(existing);
    }
 
    public void delete(Long id){
        repo.deleteById(id);
    }
 
    public double convert(double inr,String code){
 
        Currency c = repo.findAll()
                .stream()
                .filter(x -> x.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow();
 
        return inr * c.getRate();
    }
 
}
 