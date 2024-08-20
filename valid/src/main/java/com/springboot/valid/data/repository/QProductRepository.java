package com.springboot.valid.data.repository;

import com.springboot.valid.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository
        extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
}
