package com.avi.eCommerce_search.service;

import com.avi.eCommerce_search.dto.ProductDTO;
import com.avi.eCommerce_search.entity.Product;
import com.avi.eCommerce_search.mapper.ProductMapper;
import com.avi.eCommerce_search.repository.ProductRepository;
import com.avi.eCommerce_search.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getProducts(String category, BigDecimal minPrice, BigDecimal maxPrice, String brand, Double rating, Boolean available, int page, int size) {
        var spec = ProductSpecification.buildSpecification(category, minPrice, maxPrice, brand, rating, available);
        Page<Product> productPage = productRepository.findAll(spec, PageRequest.of(page, size));

        // Use MapStruct to convert entities to DTOs
        return productPage.getContent().stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .collect(Collectors.toList());
    }
}
