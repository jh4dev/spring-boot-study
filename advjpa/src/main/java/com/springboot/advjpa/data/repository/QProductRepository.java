package com.springboot.advjpa.data.repository;

import com.springboot.advjpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository
        extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
}
