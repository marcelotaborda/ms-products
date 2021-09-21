package com.taborda.productms.application.port.in;

import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.framework.errors.ResourceNotFoundException;
import com.taborda.productms.framework.errors.ValidatedParametersException;
 

public interface ProductCrudUseCase {

	List<ProductDto> search(FiltroDto dto) throws ValidatedParametersException;
	
	List<ProductDto> listAll();
 
	ProductDto findById(Long id) throws ResourceNotFoundException, ValidatedParametersException;

	ProductDto create(ProductDto dto) throws ValidatedParametersException, MethodArgumentNotValidException ;

	boolean delete(Long id) throws ResourceNotFoundException;

	ProductDto update(long id,ProductDto dto) throws ResourceNotFoundException, ValidatedParametersException, MethodArgumentNotValidException;

}