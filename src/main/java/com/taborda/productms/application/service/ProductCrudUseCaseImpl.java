package com.taborda.productms.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.application.port.in.ProductCrudUseCase;
import com.taborda.productms.application.port.out.ProductPortOut;
import com.taborda.productms.framework.errors.ResourceNotFoundException;
import com.taborda.productms.framework.errors.ValidatedParametersException;

@Service
public class ProductCrudUseCaseImpl implements ProductCrudUseCase {

	@Autowired
	private ProductPortOut productPortOut;

	@Override
	public List<ProductDto> search(FiltroDto dto) {
		return productPortOut.findFilters(dto).stream().map(element -> new ModelMapper().map(element, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto findById(Long id) throws ResourceNotFoundException, ValidatedParametersException {
		return new ModelMapper().map(productPortOut.find(id), ProductDto.class);
	}

	@Override 
	public ProductDto create(ProductDto dto) throws ValidatedParametersException{
		return new ModelMapper().map(productPortOut.create(dto), ProductDto.class);
	}

	@Override
	public boolean delete(Long id) throws ResourceNotFoundException {
		return productPortOut.delete(id);
	}

	@Override
	public ProductDto update(long id, ProductDto p) throws ResourceNotFoundException, ValidatedParametersException {
		return new ModelMapper().map(productPortOut.update(id, p), ProductDto.class);
	}

	@Override
	public List<ProductDto> listAll() {
		return productPortOut.list().stream().map(element -> new ModelMapper().map(element, ProductDto.class))
				.collect(Collectors.toList());
	}

}
