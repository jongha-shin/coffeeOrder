package service;

import java.util.List;

import dto.CoffeeDto;
import dto.OrderDto;

public interface OrderService {

	List<CoffeeDto> showMenu();	// 1. 이름만 가져오는것 2. 모든정보 가져오는거 두개 공통으로 사용

	int coffee_cost(String name, String size);

	boolean addOrder(OrderDto dto);

	List<OrderDto> showHistory(String id);

	boolean deleteHistory(String id);
}
