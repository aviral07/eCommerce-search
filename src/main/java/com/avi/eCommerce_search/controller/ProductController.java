package com.avi.eCommerce_search.controller;

import com.avi.eCommerce_search.dto.ProductDTO;
import com.avi.eCommerce_search.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public List<ProductDTO> getProducts(@RequestParam(required = false) String category,
                                        @RequestParam(required = false) BigDecimal minPrice,
                                        @RequestParam(required = false) BigDecimal maxPrice,
                                        @RequestParam(required = false) String brand,
                                        @RequestParam(required = false) Double rating,
                                        @RequestParam(required = false) Boolean available,
                                        @RequestParam int page,
                                        @RequestParam int size) {
        return productService.getProducts(category, minPrice, maxPrice, brand, rating, available, page, size);
    }
}
