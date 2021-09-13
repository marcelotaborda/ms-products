package com.taborda.productms.application.port.in;

import java.util.List;

import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.framework.errors.ResourceNotFoundException;
 

public interface ProductCrudUseCase {

	List<ProductDto> search(FiltroDto dto) ;
	
	List<ProductDto> listAll();
 
	ProductDto findById(Long id) throws ResourceNotFoundException;

	ProductDto create(ProductDto dto) ;

	boolean delete(Long id) throws ResourceNotFoundException;

	ProductDto update(long id,ProductDto dto) throws ResourceNotFoundException;

}