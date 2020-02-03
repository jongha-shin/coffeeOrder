package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dto.CoffeeDto;
import dto.MemberDto;
import service.MemberService;
import service.impl.MemberServiceImpl;
import singleton.Singleton;
import view.accountView;
import view.loginView;
import view.orderView;

// <service>와 대화
public class MemberController {

	// 컨트롤러 생성 -> 멤 서비스임플 생성 -> 멤버 다오임플 생성
	MemberService memServ = new MemberServiceImpl();
	
	public void login() {
		new loginView();
	}
	
	public void regi() {
		new accountView();
	}
	
	public void regiAf(String id, String pwd, String name, String email) {	// 매개변수로 memberDto가능
		
		//Service에 전송
		boolean b = memServ.addMember(new MemberDto(id, pwd, name, email, 3)); // 일반사용자 3번
		if(b) {
			JOptionPane.showMessageDialog(null, "회원가입 성공");
			login();	// 뷰 전송
		}else {
			JOptionPane.showMessageDialog(null, "회원가입 실패");
			regi();		// 뷰 전송
		}
	}
	
	public void loginAf(String id, String pwd) {

		MemberDto dto = memServ.login(id, pwd);
		if(dto == null) {
			JOptionPane.showMessageDialog(null, "id나 password가 틀렸습니다");
			login();
		}else {
			JOptionPane.showMessageDialog(null, dto.getId()+"님 환영합니다");
			
			// id 저장 ( web : session에 저장)
			Singleton s = Singleton.getInstance();
			s.setLoginID(dto.getId());
			
			// order Controller - orderview로 이동
			s.orderView = new orderView();
			
		}
	}
	
	public boolean idCheck(String id) {
		return memServ.getId(id);
	}
	
	
}
