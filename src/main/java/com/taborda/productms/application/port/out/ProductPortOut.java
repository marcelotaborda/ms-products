package com.taborda.productms.application.port.out;

 
import java.util.List;

import com.taborda.productms.application.domain.Product;
import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.framework.errors.ResourceNotFoundException;
 

public interface ProductPortOut {
	Product create(ProductDto p);
	Product update(long id,ProductDto p) throws ResourceNotFoundException;
	Product find(Long id) throws ResourceNotFoundException;
	boolean delete(Long id) throws ResourceNotFoundException;
	List<Product> search(FiltroDto dto) ;
	List<Product> list();
} 
