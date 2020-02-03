package service.impl;

import java.util.List;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import dto.CoffeeDto;
import dto.OrderDto;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrderDao dao = new OrderDaoImpl();

	@Override
	public List<CoffeeDto> showMenu() {
		
		return dao.showMenu();
	}

	@Override
	public int coffee_cost(String name, String size) {
		
		return dao.coffee_cost(name, size);
	}

	@Override
	public boolean addOrder(OrderDto dto) {
		
		return dao.addOrder(dto);
	}

	@Override
	public List<OrderDto> showHistory(String id) {
		
		return dao.showHistory(id);
	}

	@Override
	public boolean deleteHistory(String id) {
		
		return dao.deleteHistory(id);
		
	}
}
