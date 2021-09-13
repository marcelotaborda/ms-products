package com.taborda.productms.framework.adapter.rest.out;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taborda.productms.application.domain.Product;
import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.application.port.out.ProductPortOut;
import com.taborda.productms.framework.adapter.repository.ProductRepository;
import com.taborda.productms.framework.errors.ResourceNotFoundException;

@Component
public class ProductPersistence implements ProductPortOut {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product create(ProductDto p) {
 		 return productRepository.save(p.converter());
	}

	@Override
	public Product update(long id, ProductDto p) throws ResourceNotFoundException {   
		
		Product  product = productRepository.getById(id);
		if(null == product) {
			
			throw new ResourceNotFoundException("No records found for this ID");
			
		}
		
		product.setName(p.getName());
		product.setDescription(p.getDescription());
	    product.setPrice(p.getPrice());
		return product;
	}

	@Override
	public boolean delete(Long id) throws ResourceNotFoundException{
		Optional<Product>  optional = productRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ResourceNotFoundException("No records found for this ID");
			
		}
	 	productRepository.delete(optional.get());
		return true;
	}

	@Override
	public List<Product> search(FiltroDto dto)   {
		 	return productRepository.search(dto.getQ(),dto.getMin_price(), dto.getMax_price());
 	}

	@Override
	public Product find(Long id) throws ResourceNotFoundException {
		Optional<Product>  optional = productRepository.findById(id);
		if(!optional.isPresent()) {
			throw new ResourceNotFoundException("No records found for this ID");
			
		}
	 	
		return optional.get();
	}

	@Override
	public List<Product> list() {
 		return productRepository.findAll(); 
	}

}
