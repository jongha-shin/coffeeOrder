package main;

import db.DBConnection;
import singleton.Singleton;
import view.orderingView;

public class main {
	
	public static void main(String[] args) {
		DBConnection.initConnection();
		
		Singleton s = Singleton.getInstance();
		s.memCtrl.login();
		
	}
}
