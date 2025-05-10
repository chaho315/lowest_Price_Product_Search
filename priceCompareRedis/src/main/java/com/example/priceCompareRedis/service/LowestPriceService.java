package com.example.priceCompareRedis.service;

import com.example.priceCompareRedis.vo.Keyword;
import com.example.priceCompareRedis.vo.Product;
import com.example.priceCompareRedis.vo.ProductGrp;

import java.util.Set;

public interface LowestPriceService {
    public Set<?> getZsetValue(String key);
    Set<?> GetZsetValueWithStatus(String key) throws Exception;
    Set<String> GetZsetValueWithSpecificException(String key) throws Exception;
    int SetNewProduct(Product newProduct);
    int SetNewProductGrp(ProductGrp newProductGrp);
    int SetNewProductGrpToKeyword (String keyword, String prodGrpId, double score);
    Keyword GetLowestPriceProductByKeyword(String keyword);
}
