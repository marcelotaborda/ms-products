package com.taborda.productms.framework.adapter.rest.in;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.taborda.productms.application.domain.dto.FiltroDto;
import com.taborda.productms.application.domain.dto.ProductDto;
import com.taborda.productms.application.port.in.ProductCrudUseCase;
import com.taborda.productms.framework.errors.Error;
import com.taborda.productms.framework.errors.ResourceNotFoundException;
import com.taborda.productms.framework.errors.ValidatedParametersException;

@RestController
@RequestMapping("/products")
public class ProductRest {

	@Autowired
	ProductCrudUseCase productCrudUseCase;

	/**
	 * List all product in catalog
	 * 
	 * @return
	 */
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().header("DataEncoding", "utf-8").body(productCrudUseCase.listAll());

	}

	/**
	 * Filter product -  
	 * @param q (optional)
	 * @param min_price (optional/require valid number)
	 * @param max_price (optional/require valid number)
	 * @return
	 */
	@GetMapping(value = "/search", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> search(@Validated FiltroDto dto) {

		List<ProductDto> lista;
		try {
			
			dto.isValid();
			
			lista = productCrudUseCase.search(dto);   
	 		
			
		} catch (ValidatedParametersException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(400, "Invalid value min_price/max_price"));
		}

		return ResponseEntity.ok().header("DataEncoding", "utf-8").body(lista);
	}

	/**
	 * Find product by id valid
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findById(@PathVariable Long id) {
		ProductDto productDto = null;
		try {
			productDto = productCrudUseCase.findById(id);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(404, "Product Not found"));
		}

		return ResponseEntity.ok().header("DataEncoding", "utf-8").body(productDto);
	}

	/**
	 * Create new product
	 * @param dto
	 * @param uriBuilder
	 * @return
	 */
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> create(@RequestBody @Validated ProductDto dto, Errors errors,
			UriComponentsBuilder uriBuilder) {

		if (errors.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(400, errors.getFieldError().getField() + " - " + errors.getFieldError().getDefaultMessage()));
		}

		ProductDto productDto = productCrudUseCase.create(dto);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(productDto.getId()).toUri();
		return ResponseEntity.created(uri).body(productDto);
	}

	/**
	 * Remove product
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> delete(@PathVariable Long id) {

		try {
			productCrudUseCase.delete(id);

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(404, "ID not found"));
		}

		return ResponseEntity.ok().build();

	}

	/**
	 * Update data products
	 * @param id
	 * @param dto
	 * @return
	 */
	@PutMapping(value = "/{id}", produces = { "application/json", "application/xml",
			"application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Validated ProductDto dto, Errors errors) {

		if (errors.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(400, errors.getFieldError().getField() + " - " + errors.getFieldError().getDefaultMessage()));
		}

		ProductDto productDto = null;
		try {
			productDto = productCrudUseCase.update(id, dto);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
					.body(new Error(404, "Product not modified - ID not found"));
		}

		return ResponseEntity.ok().header("DataEncoding", "utf-8").body(productDto);

	}
}
