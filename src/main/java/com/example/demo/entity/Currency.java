package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



import jakarta.persistence.*;
 
@Entity
public class Currency {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String country;
    private String code;
    private double rate; 
    
    
    public Currency(String country, String code, double rate) {
		super();
		this.country = country;
		this.code = code;
		this.rate = rate;
	}

	Currency()
    {
    	
    }
 
    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }
 
    public String getCountry(){ return country; }
    public void setCountry(String country){ this.country=country; }
 
    public String getCode(){ return code; }
    public void setCode(String code){ this.code=code; }
 
    public double getRate(){ return rate; }
    public void setRate(double rate){ this.rate=rate; }
}
 