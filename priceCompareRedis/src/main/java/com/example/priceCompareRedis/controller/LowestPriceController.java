package com.example.priceCompareRedis.controller;

import com.example.priceCompareRedis.service.LowestPriceService;

import com.example.priceCompareRedis.vo.Keyword;
import com.example.priceCompareRedis.vo.Product;
import com.example.priceCompareRedis.vo.ProductGrp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/")
public class LowestPriceController {

    @Autowired
    private LowestPriceService mlps;

    @GetMapping("/product")
    public Set<?> GetZsetValue(@RequestParam(name = "key", defaultValue = "") String key){
        return mlps.getZsetValue(key);
    }
    
    //Exception 처리1
    @GetMapping("/product1")
    public Set GetZsetValueWithStatus (String key){
        try {
            return mlps.GetZsetValueWithStatus(key);
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
    //Exception 처리2
    @GetMapping("/product2")
    public Set GetZsetValueUsingExController (String key) throws Exception {
        try {
            return mlps.GetZsetValueWithStatus(key);
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    //Exception 처리3
    @GetMapping("/product3")
    public ResponseEntity<Set> GetZsetValueUsingExControllerWithSpecificException (String key) throws Exception {
        Set<String> mySet = new HashSet<>();
        try {
            mySet =  mlps.GetZsetValueWithSpecificException(key);
        }
        catch (ChangeSetPersister.NotFoundException ex) {
            throw new Exception(ex);
        }
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<Set>(mySet, responseHeaders, HttpStatus.OK);
    }

    @PutMapping("/product")
    public int SetNewProduct(@RequestBody Product newProduct) {
        return mlps.SetNewProduct(newProduct);

    }

    @PutMapping("/productGroup")
    public int SetNewProductGroup(@RequestBody ProductGrp newProductGrp) {
        return mlps.SetNewProductGrp(newProductGrp);
    }

    @PutMapping("/productGroupToKeyword")
    public int SetNewProductGrpToKeyword (String keyword, String prodGrpId, double score) {
        return mlps.SetNewProductGrpToKeyword(keyword, prodGrpId, score);
    }

    @GetMapping("/productPrice/lowest")
    public Keyword GetLowestPriceProductByKeyword (String keyword) {
        return mlps.GetLowestPriceProductByKeyword(keyword);
    }
}
