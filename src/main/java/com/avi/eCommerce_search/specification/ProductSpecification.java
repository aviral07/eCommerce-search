package com.avi.eCommerce_search.specification;

import com.avi.eCommerce_search.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> categoryEquals(String category) {
        return (root, query, builder) -> {
            if (category != null && !category.isEmpty()) {
                return builder.equal(root.get("category"), category);
            }
            return builder.conjunction();
        };
    }

    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, builder) -> {
            if (minPrice != null && maxPrice != null) {
                return builder.between(root.get("price"), minPrice, maxPrice);
            }
            return builder.conjunction();
        };
    }

    public static Specification<Product> brandEquals(String brand) {
        return (root, query, builder) -> {
            if (brand != null && !brand.isEmpty()) {
                return builder.equal(root.get("brand"), brand);
            }
            return builder.conjunction();
        };
    }

    public static Specification<Product> ratingGreaterThan(Double rating) {
        return (root, query, builder) -> {
            if (rating != null) {
                return builder.greaterThanOrEqualTo(root.get("rating"), rating);
            }
            return builder.conjunction();
        };
    }

    public static Specification<Product> available(Boolean available) {
        return (root, query, builder) -> {
            if (available != null) {
                return builder.equal(root.get("available"), available);
            }
            return builder.conjunction();
        };
    }

    public static Specification<Product> buildSpecification(String category, BigDecimal minPrice, BigDecimal maxPrice, String brand, Double rating, Boolean available) {
        Specification<Product> spec = Specification.where(null);

        if (category != null && !category.isEmpty()) {
            spec = spec.and(categoryEquals(category));
        }

        if (minPrice != null && maxPrice != null) {
            spec = spec.and(priceBetween(minPrice, maxPrice));
        }

        if (brand != null && !brand.isEmpty()) {
            spec = spec.and(brandEquals(brand));
        }

        if (rating != null) {
            spec = spec.and(ratingGreaterThan(rating));
        }

        if (available != null) {
            spec = spec.and(available(available));
        }

        return spec;
    }
}