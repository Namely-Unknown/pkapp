package com.plantkeeper.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plantkeeper.business.ReturnItemView;
import com.plantkeeper.dto.CompanyDTO;
import com.plantkeeper.dto.OrderItemDTO;
import com.plantkeeper.dto.ReturnItemDTO;
import com.plantkeeper.entity.Company;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.service.CompanyService;
import com.plantkeeper.service.OrderItemService;
import com.plantkeeper.service.ReturnItemService;

@RestController
public class ReturnItemController {

	@Autowired
	private ReturnItemService service;

	@Autowired
	private OrderItemService oiService;
	
	@Autowired
	private CompanyService companyService;

	@PostMapping("/api/returnitem")
	private ResponseEntity<ReturnItemView> addReturnItem(@RequestBody ReturnItemDTO dto) {

		System.out.println(dto);
		
		Optional<OrderItemDTO> item = oiService.findById(dto.getOrderItemId());

		if (item.isPresent()) {
			dto.setCreated(LocalDate.now());
			ReturnItemDTO savedReturn = service.save(dto);
			if (!savedReturn.isFundsToAccount()) {
				System.out.println("remove from invoice");
				oiService.removeUnits(item.get().getId(), savedReturn.getUnits());
			} else {
				CompanyDTO client = companyService.findByOrderItemId(dto.getOrderItemId());
				client.setFundsOnAccount(client.getFundsOnAccount().subtract(item.get().getUnitPrice().multiply(BigDecimal.valueOf(dto.getUnits()))));
				companyService.save(client);
			}
			return new ResponseEntity<>(service.mapToView(savedReturn), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/returnitem/{id}")
	private ResponseEntity<ReturnItemView> getReturnItem(@PathVariable Long id) {
		Optional<ReturnItemDTO> returnItem = service.findById(id);
		if (returnItem.isPresent()) {
			return new ResponseEntity<>(service.mapToView(returnItem.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/api/returnitems/orderitem/{id}")
	private ResponseEntity<List<ReturnItemView>> getReturnItemByOrderItem(@PathVariable("id") Long orderItemId) {
		List<ReturnItemView> items = service.findByOrderItemId(orderItemId).stream()
				.map(returnItem -> service.mapToView(returnItem)).collect(Collectors.toList());

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping("/api/returnitems/order/{id}")
	private ResponseEntity<List<ReturnItemView>> getReturnItemByOrder(@PathVariable("id") Long orderId) {
		
		System.out.println("Return Order ID " + orderId);
		
		List<ReturnItemView> items = service.findByOrderId(orderId).stream()
				.map(returnItem -> service.mapToView(returnItem)).collect(Collectors.toList());

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@PutMapping("/api/returnitem")
	private ResponseEntity<ReturnItemView> editReturnItem(@RequestBody ReturnItemDTO dto) {
		return service.findById(dto.getId()).map(returnItem -> {
			returnItem.setOrderItemId(dto.getOrderItemId());
			returnItem.setUnits(dto.getUnits());

			return new ResponseEntity<>(service.mapToView(service.save(returnItem)), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}

	@DeleteMapping("/api/returnitem")
	private ResponseEntity<ReturnItemView> deleteReturnItem(@RequestBody ReturnItemDTO dto) {
		if (service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.BAD_REQUEST);
		}
	}
}
