package com.taborda.productms.framework.adapter.rest.in;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import com.taborda.productms.framework.errors.ResourceNotFoundException;
import com.taborda.productms.framework.errors.ValidatedParametersException;

@RestController
@RequestMapping("/products")
public class ProductRest {

	private static final String UTF_8 = "utf-8";
	private static final String DATA_ENCODING = "DataEncoding";
	@Autowired
	ProductCrudUseCase productCrudUseCase;

	/**
	 * List all product in catalog
	 * 
	 * @return
	 */
	@GetMapping 
	public ResponseEntity<List<ProductDto>> list() {
		return ResponseEntity.ok().header(DATA_ENCODING, UTF_8).body(productCrudUseCase.listAll());

	}

	/**
	 * Filter product -
	 * 
	 * @param q         (optional)
	 * @param min_price (optional/require valid number)
	 * @param max_price (optional/require valid number)
	 * @return
	 * @throws ValidatedParametersException
	 */
	@GetMapping(value = "/search")
	public ResponseEntity<List<ProductDto>> search(@Valid FiltroDto dto) throws ValidatedParametersException {
		dto.isValid();
		return ResponseEntity.ok().header(DATA_ENCODING, UTF_8).body(productCrudUseCase.search(dto));
	}

	/**
	 * Find product by id valid
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping(value = "/{id}" )
	public ResponseEntity<ProductDto> findById(@PathVariable Long id)
			throws ResourceNotFoundException, ValidatedParametersException {
		return ResponseEntity.ok().header(DATA_ENCODING, UTF_8).body(productCrudUseCase.findById(id));
	}

	/**
	 * Create new product
	 * 
	 * @param dto
	 * @param uriBuilder
	 * @return
	 */
	@PostMapping 
	public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductDto dto, UriComponentsBuilder uriBuilder)
			throws ValidatedParametersException, MethodArgumentNotValidException {
		
		ProductDto productDto = productCrudUseCase.create(dto);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(productDto.getId()).toUri();
		return ResponseEntity.created(uri).body(productDto);
	}

	/**
	 * Remove product
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping(value = "/{id}" )
	public ResponseEntity<ProductDto> delete(@PathVariable Long id)
			throws ResourceNotFoundException, ValidatedParametersException {

		productCrudUseCase.delete(id);

		return ResponseEntity.ok().build();

	}

	/**
	 * Update data products
	 * 
	 * @param id
	 * @param dto
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductDto dto, UriComponentsBuilder uriBuilder)
			throws ResourceNotFoundException, ValidatedParametersException,MethodArgumentNotValidException {

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(dto.getId()).toUri(); 
 		return ResponseEntity.created(uri).body(productCrudUseCase.update(id, dto));

	}
}
