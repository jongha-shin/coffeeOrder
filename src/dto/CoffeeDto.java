package dto;

public class CoffeeDto {
	private String coffeeName;
	private int size_short;
	private int size_Tall;
	private int size_Grande;
	
	public CoffeeDto() {
		
	}

	public CoffeeDto(String coffeeName, int size_short, int size_Tall, int size_Grande) {
		super();
		this.coffeeName = coffeeName;
		this.size_short = size_short;
		this.size_Tall = size_Tall;
		this.size_Grande = size_Grande;
	}

	public String getCoffeeName() {
		return coffeeName;
	}

	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}

	public int getSize_short() {
		return size_short;
	}

	public void setSize_short(int size_short) {
		this.size_short = size_short;
	}

	public int getSize_Tall() {
		return size_Tall;
	}

	public void setSize_Tall(int size_Tall) {
		this.size_Tall = size_Tall;
	}

	public int getSize_Grande() {
		return size_Grande;
	}

	public void setSize_Grande(int size_Grande) {
		this.size_Grande = size_Grande;
	}

	@Override
	public String toString() {
		return "CoffeeDto [coffeeName=" + coffeeName + ", size_short=" + size_short + ", size_Tall=" + size_Tall
				+ ", size_Grande=" + size_Grande + "]";
	}
	
	
}
