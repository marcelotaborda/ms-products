package com.taborda.productms.framework.adapter.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.taborda.productms.application.domain.Product;

public final class FilterProductSpecification {

	public static Specification<Product> findProduct(String q, double min_price, double max_price) {
		return new Specification<Product>() {

			Predicate predicateLike = null;
			Predicate predicateMinPrice = null;
			Predicate predicateMaxPrice = null;

			private static final long serialVersionUID = -3911920365171490223L;

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<Predicate>();

				if (null != q) {
					predicateLike = criteriaBuilder.or(criteriaBuilder.like(root.get("name"), "%" + q + "%"),
							criteriaBuilder.like(root.get("description"), "%" + q + "%"));
					predicates.add(predicateLike);
				}

				if (min_price >= 0) {
					predicateMinPrice = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min_price);
					predicates.add(predicateMinPrice);
				}

				if (max_price >= 0) {
					predicateMaxPrice = criteriaBuilder.lessThanOrEqualTo(root.get("price"), max_price);
					predicates.add(predicateMaxPrice);
				}

				return query.where(predicates.toArray(Predicate[]::new)).getRestriction();
			}
		};

	}

}
