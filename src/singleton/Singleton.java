package singleton;


import controller.MemberController;
import controller.OrderController;
import view.orderView;

public class Singleton {

	private static Singleton s = null;
	
	public MemberController memCtrl = null;
	public OrderController orderCtrl = null;
	public orderView orderView = null;
	
	private String loginID = null;
	
	private Singleton() {
		memCtrl = new MemberController();
		orderCtrl = new OrderController();
		
	}
	
	public static Singleton getInstance() {
		if(s == null) {
			s = new Singleton();
		}
		return s;
	}
	
	
	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

}
