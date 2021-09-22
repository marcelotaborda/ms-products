package com.taborda.productms.framework.adapter.persistence.out;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.taborda.productms.application.domain.Product;
import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.application.port.out.ProductPortOut;
import com.taborda.productms.framework.adapter.repository.FilterProductSpecification;
import com.taborda.productms.framework.adapter.repository.ProductRepository;
import com.taborda.productms.framework.errors.ResourceNotFoundException;

@Component
public class ProductPersistence implements ProductPortOut {

	private static final String NO_RECORDS_FOUND_FOR_THIS_ID = "No records found for this ID";
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product create(ProductDto p) {
		return productRepository.save(new ModelMapper().map(p, Product.class));
	}

	@Override
	public Product update(long id, ProductDto p) throws ResourceNotFoundException {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS_FOUND_FOR_THIS_ID));

		product.setName(p.getName());
		product.setDescription(p.getDescription());
		product.setPrice(p.getPrice());
		productRepository.save(product);
		return product;
	}

	@Override
	public boolean delete(Long id) throws ResourceNotFoundException {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS_FOUND_FOR_THIS_ID));

		productRepository.delete(product);
		return true;
	}

	@Override
	public List<Product> findFilters(FiltroDto dto) {
		return productRepository.findAll(Specification
				.where(FilterProductSpecification.findFilters(dto.getQ(), dto.getMinPricedb(), dto.getMaxPricedb())));
	}

	@Override
	public Product find(Long id) throws ResourceNotFoundException {

		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS_FOUND_FOR_THIS_ID));
	}

	@Override
	public List<Product> list() {
		return productRepository.findAll();
	}

}
