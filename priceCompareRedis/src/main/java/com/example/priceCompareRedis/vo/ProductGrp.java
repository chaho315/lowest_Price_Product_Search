package com.example.priceCompareRedis.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductGrp {
    private String prodGrpId;//FPG0001
    private List<Product> productList;//[{dlfc, 25000}. {}...]
}
