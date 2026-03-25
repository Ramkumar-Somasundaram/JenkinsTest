package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Currency;
import com.example.demo.entity.User;
import com.example.demo.service.CurrencyService;
 
@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/currency")
public class CurrencyController {
 
    private final CurrencyService service;
 
    public CurrencyController(CurrencyService service){
        this.service = service;
    }
 
    @GetMapping("/get")
    public List<Currency> getAll(){
        return service.getAll();
    }
 
    @PostMapping("/post")
    public Currency add(@RequestBody Currency c){
        return service.add(c);
    }
 
    @PutMapping("/{id}")
    public Currency update(@PathVariable Long id,
                           @RequestBody Currency c){
        return service.update(id,c);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
 
    @PostMapping("/convert")
    public double convert(@RequestBody User user){
    	
    	int inr=user.getInr();
    	String code=user.getCode();
 
        return service.convert(inr,code);
    }
 
}
 