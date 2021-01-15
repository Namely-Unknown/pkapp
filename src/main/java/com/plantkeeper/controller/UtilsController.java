package com.plantkeeper.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plantkeeper.dto.LostProductDTO;
import com.plantkeeper.dto.ProductDTO;
import com.plantkeeper.service.LostProductService;
import com.plantkeeper.service.ProductService;
import com.plantkeeper.utils.LostProductCode;
import com.plantkeeper.utils.OrderStatus;
import com.plantkeeper.utils.States;

@RestController
public class UtilsController {
	
	@Autowired
	private LostProductService service;
	@Autowired
	private ProductService productService;

	@GetMapping("/api/order_statuses")
	public List<OrderStatus> getOrderStatuses(){
		return Arrays.asList(OrderStatus.values());
	}
	
	@GetMapping("/api/states")
	public List<States> getStates(){
		return Arrays.asList(States.values());
	}	
	
	@GetMapping("/api/losscodes")
	public List<Map<LostProductCode, String>> getLossCodes(){
		
		List<Map<LostProductCode, String>> returnList = new ArrayList<Map<LostProductCode, String>>();
		
		for (LostProductCode code : LostProductCode.values()) {
			Map<LostProductCode, String> lossCodes = new HashMap<LostProductCode, String>();
			lossCodes.put(code, code.toString());
			returnList.add(lossCodes);
		}
		return returnList;
	}
	
	@PostMapping("/api/lostproduct")
	public ResponseEntity<LostProductDTO> addLostProduct(@RequestBody LostProductDTO dto){
		
		Optional<ProductDTO> product = productService.findById(dto.getProductId());
		if (product.isPresent()) {
			dto.setLogDate(LocalDate.now());
			LostProductDTO lost = service.save(dto);
			productService.removeUnits(dto.getProductId(), lost.getUnits());
			return new ResponseEntity<>(lost, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
