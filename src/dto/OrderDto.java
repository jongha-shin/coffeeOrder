package dto;

import java.util.Date;

public class OrderDto {
	private int orderID;
	private String coffee;	//1
	private String id;				
	private String orderDate;	//2
	private String size;	//3
	private int count;		//4
	private int totalCost;	//5
	private int delete;
	
	public OrderDto() {
		
	}
	public OrderDto(int orderID, String coffee, String id, String orderDate, String size, int count, int totalCost, int delete) {
		super();
		this.orderID = orderID;
		this.coffee = coffee;
		this.id = id;
		this.orderDate = orderDate;
		this.size = size;
		this.count = count;
		this.totalCost = totalCost;
		this.delete = delete;
	}
	public OrderDto(String coffee, String orderDate, String size, int count, int totalCost) {
		this.coffee = coffee;
		this.orderDate = orderDate;
		this.size = size;
		this.count = count;
		this.totalCost = totalCost;
		
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getCoffee() {
		return coffee;
	}
	public void setCoffee(String coffee) {
		this.coffee = coffee;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	@Override
	public String toString() {
		return "OrderDto [orderID=" + orderID + ", coffee=" + coffee + ", id=" + id + ", orderDate=" + orderDate
				+ ", size=" + size + ", count=" + count + ", totalCost=" + totalCost + "]";
	}
	
	
}
