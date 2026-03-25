package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Currency;
import com.example.demo.repo.CurrencyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CurrencyServiceTest {

    @Mock
    private CurrencyRepo repo;

    @InjectMocks
    private CurrencyService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   
    
    @Test
    void testGetAll() {
        List<Currency> list = Arrays.asList(
                new Currency("USA", "USD", 0.012),
                new Currency("EUROPE", "EUR", 0.011)
        );

        when(repo.findAll()).thenReturn(list);

        List<Currency> result = service.getAll();

        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }

  
    @Test
    void testAdd() {
        Currency c = new Currency("Japan", "JPY", 1.60);

        when(repo.save(c)).thenReturn(c);

        Currency saved = service.add(c);

        assertEquals("Japan", saved.getCountry());
        verify(repo, times(1)).save(c);
    }

 
    @Test
    void testUpdate() {
        Currency existing = new Currency("USA", "USD", 0.012);
        Currency update = new Currency(null, "United States", 0.013);

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);

        Currency updated = service.update(1L, update);

        assertEquals("United States", updated.getCountry());
        assertEquals(0.013, updated.getRate());
        verify(repo, times(1)).findById(1L);
        verify(repo, times(1)).save(existing);
    }

   
    @Test
    void testDelete() {
        doNothing().when(repo).deleteById(1L);

        service.delete(1L);

        verify(repo, times(1)).deleteById(1L);
    }

  
    @Test
    void testConvert() {
        Currency usd = new Currency("USA", "USD", 0.012);

        when(repo.findAll()).thenReturn(List.of(usd));

        double result = service.convert(100, "USD");

        assertEquals(1.2, result);   // 100 * 0.012
        verify(repo, times(1)).findAll();
    }
}