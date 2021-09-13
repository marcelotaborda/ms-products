package com.taborda.productms.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.application.port.in.ProductCrudUseCase;
import com.taborda.productms.application.port.out.ProductPortOut;
import com.taborda.productms.framework.errors.ResourceNotFoundException;

@Service
public class ProductCrudUseCaseImpl implements ProductCrudUseCase {

	@Autowired
	private ProductPortOut productPortOut;

	@Override
	public List<ProductDto> search(FiltroDto dto) {

		return ProductDto.convertList(productPortOut.search(dto));
	}

	@Override
	public ProductDto findById(Long id) throws ResourceNotFoundException{
		return new ProductDto(productPortOut.find(id));
	}

	@Override
	public ProductDto create(ProductDto dto) {
		return new ProductDto(productPortOut.create(dto));
	}

	@Override
	public boolean delete(Long id) throws ResourceNotFoundException{
		return productPortOut.delete(id);
	}

	@Override
	public ProductDto update(long id, ProductDto p) throws ResourceNotFoundException{
		return new ProductDto(productPortOut.update(id, p));
	}

	@Override
	public List<ProductDto> listAll() {
		return ProductDto.convertList(productPortOut.list());
	}

}
