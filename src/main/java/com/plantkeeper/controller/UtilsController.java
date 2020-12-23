package com.plantkeeper.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plantkeeper.utils.OrderStatus;
import com.plantkeeper.utils.States;

@RestController
public class UtilsController {

	@GetMapping("/api/order_statuses")
	public List<OrderStatus> getOrderStatuses(){
		return Arrays.asList(OrderStatus.values());
	}
	
	@GetMapping("/api/states")
	public List<States> getStates(){
		return Arrays.asList(States.values());
	}	
}
