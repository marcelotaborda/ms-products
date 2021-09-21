package com.taborda.productms.framework.adapter.repository;

import java.io.Serializable;
import java.util.ArrayList;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.taborda.productms.application.domain.Product;

 
public final class FilterProductSpecification implements Serializable {

	 
	private static final long serialVersionUID = 2638344318581596899L;

	private  FilterProductSpecification() {
 	}

	public static Specification<Product> findFilters(String q, double minPrice, double maxPrice) {
		return new Specification<Product>() {

			transient  Predicate predicateLike = null;
			transient Predicate predicateMinPrice = null;
			transient  Predicate predicateMaxPrice = null;

			private static final long serialVersionUID = -3911920365171490223L;

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();

				if (null != q) {
					predicateLike = criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),  criteriaBuilder.lower(criteriaBuilder.literal("%" + q + "%"))),
							criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), criteriaBuilder.lower(criteriaBuilder.literal("%" + q + "%"))));
					predicates.add(predicateLike);
				}

				if (minPrice >= 0) {
					predicateMinPrice = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
					predicates.add(predicateMinPrice);
				}

				if (maxPrice >= 0) {
					predicateMaxPrice = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
					predicates.add(predicateMaxPrice);
				}

				return query.where(predicates.toArray(Predicate[]::new)).getRestriction();
			}

			 
		};

	}

}
