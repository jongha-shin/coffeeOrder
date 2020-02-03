package dao;

import java.util.List;

import dto.CoffeeDto;
import dto.OrderDto;

public interface OrderDao {

	public List<CoffeeDto> showMenu();

	public int coffee_cost(String name, String size);
	
	public boolean addOrder(OrderDto dto);

	public List<OrderDto> showHistory(String id);

	public boolean deleteHistory(String id);
	

}
