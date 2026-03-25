package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Currency;

public interface CurrencyRepo  extends JpaRepository<Currency,Long>{

}
