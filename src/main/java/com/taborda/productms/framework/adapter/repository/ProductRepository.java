package com.taborda.productms.framework.adapter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.taborda.productms.application.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{

	 
	default List<Product> findFilters(String q, double minPrice, double maxPrice) {
	    return findAll(FilterProductSpecification.findFilters( q, minPrice,  maxPrice));
	  }
 
}
