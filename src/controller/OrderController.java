package controller;

import java.util.List;

import dto.CoffeeDto;
import dto.OrderDto;
import service.OrderService;
import service.impl.OrderServiceImpl;
import view.menuView;

public class OrderController {
	OrderService orderServ = new OrderServiceImpl();
	
	public void showMenu() {
		List<CoffeeDto> list = orderServ.showMenu();
		new menuView(list);
	
	}
	public List<CoffeeDto> getMenuName() {
		List<CoffeeDto> list = orderServ.showMenu();
		return list;
	}
	
	public int coffee_cost(String name, String size) {
		int cost = orderServ.coffee_cost(name, size);
		return cost;
	}
	
	public boolean addOrder(OrderDto dto) {
		boolean b = orderServ.addOrder(dto);
		return b;
	}
	
	public List<OrderDto> showHistory(String id){
		List<OrderDto> list = orderServ.showHistory(id);
		return list;
	}
	
	public boolean deleteHistory(String id){
		boolean b = orderServ.deleteHistory(id);
		return b;
	}
}
